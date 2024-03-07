package m3.uf5.pt1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int AMPLE_LEFT = 15;
	public static final int GAP = 3;
	public static final int AMPLE_CONTENT = 60;
	public HashSet<Usuari> usuaris;
	public TreeSet<Entrada> entrades;

	public Blog() {
		// TODO intentar leer del archivo
		usuaris = new HashSet<>();
		entrades = new TreeSet<>();
	}

	public HashSet<Usuari> getUsuaris() {
		return usuaris;
	}

	public void setUsuaris(HashSet<Usuari> usuaris) {
		this.usuaris = usuaris;
	}

	public TreeSet<Entrada> getEntrades() {
		return entrades;
	}

	public void setEntrades(TreeSet<Entrada> entrades) {
		this.entrades = entrades;
	}

	private Entrada cercarEntradaPerDataTitol(Date data, String titol) throws Exception {
		if (titol != null && data != null) {
			for (Entrada entrada : entrades) {
				if (toCalendar(entrada.getData()).equals(toCalendar(data)) && entrada.getTitol().equals(titol)) {
					return entrada;
				}
			}
			return null;

		} else {
			throw new Exception("Si us plau introdueix una data i un titol.");
		}
	}

	private Usuari cercarUsuariPerMail(String mail) throws Exception {
		if (mail != null) {
			for (Usuari usuari : usuaris) {
				if (usuari.getMail().equals(mail)) {
					return usuari;
				}
			}
			return null;

		} else {
			throw new Exception("Has d'introduir un mail.");
		}
	}

	private Usuari cercarUsuariPerNick(String nick) throws Exception {
		if (nick != null) {
			for (Usuari usuari : usuaris) {
				if (usuari.getNick().equals(nick)) {
					return usuari;
				}
			}
			return null;

		} else {
			throw new Exception("Has d'introduir un nick.");
		}
	}

	public void nouUsuari(String nick, String mail) throws Exception {
		if (cercarUsuariPerMail(mail) == null && cercarUsuariPerNick(nick) == null) {
			Usuari nouUsuari = new Usuari(nick, mail);
			usuaris.add(nouUsuari);

		} else {
			throw new Exception("Aquest usuari ja existeix.");
		}
	}

	public void afegirEntrada(String mail, String titol, String text) throws Exception {
		Entrada entradaRepetida = cercarEntradaPerDataTitol(new Date(), titol);

		if (entradaRepetida == null) {
			Usuari usuariEscriptor = cercarUsuariPerMail(mail);

			if (usuariEscriptor == null) {
				throw new Exception("No hi ha cap usuari amb aquest mail.");
			}

			Entrada novaEntrada = new Entrada(usuariEscriptor, titol, text);
			entrades.add(novaEntrada);

			for (Usuari usuari : usuaris) {
				if (usuari.equals(usuariEscriptor)) {
					usuari.afegirPublicacio(novaEntrada);
				}
			}
		} else {
			throw new Exception("Aquesta entrada ja existeix.");
		}
	}

	public void comentarEntrada(String mail, Date data, String titol, String text, int valoracio) throws Exception {
		Entrada entradaRepetida = cercarEntradaPerDataTitol(data, titol);

		if (entradaRepetida != null) {
			Usuari usuariEscriptor = cercarUsuariPerMail(mail);

			if (usuariEscriptor == null) {
				throw new Exception("No hi ha cap usuari amb aquest mail");
			}

			for (Entrada entrada : entrades) {
				if (entrada.compareTo(entradaRepetida) == 0) {
					entrada.afegirComentari(usuariEscriptor, text, valoracio);
				}
			}

			for (Usuari usuari : usuaris) {
				if (usuari.equals(usuariEscriptor)) {
					usuari.afegirPublicacio(new Comentari(usuari, text, valoracio));
				}
			}
		} else {
			throw new Exception("No hi ha cap entrada per poder comentar.");
		}
	}

	public String imprimirEntrada(Date data, String titol) throws Exception {
		// TODO Si existeix una entrada pel dia «data» amb el mateix «titol», retorna el
		// text d’aquesta entrada incloent els comentaris. En cas contrari llença
		// excepció.
		Entrada entradaImprimible = cercarEntradaPerDataTitol(data, titol);

		if (entradaImprimible != null) {
			return entradaImprimible.imprimirPublicacio("", AMPLE_CONTENT);

		} else {
			throw new Exception("No hi ha cap entrada que es pugui imprimir.");
		}
	}

	public String imprimirBlog() throws Exception {
		String blogSencer = "";

		// Header del blog
		blogSencer += (StringUtils.repeat(" ", AMPLE_CONTENT + GAP + AMPLE_LEFT));
		blogSencer += (System.lineSeparator());

		blogSencer += (StringUtils.center(
				"BLOG UF5 - PE1 (" + usuaris.size() + " usuaris/es, " + entrades.size() + " entrades)",
				AMPLE_CONTENT + GAP + AMPLE_LEFT, " "));
		blogSencer += (System.lineSeparator());

		blogSencer += (StringUtils.repeat("^", AMPLE_CONTENT + GAP + AMPLE_LEFT));
		blogSencer += (System.lineSeparator());

		blogSencer += (StringUtils.repeat(" ", AMPLE_CONTENT + GAP + AMPLE_LEFT));
		blogSencer += (System.lineSeparator());

		// Cada una de les entrades del blog
		Iterator<Entrada> it = entrades.iterator();

		while (it.hasNext()) {
			Entrada entrada = it.next();

			blogSencer += (imprimirEntrada(entrada.getData(), entrada.getTitol()));
		}

		return blogSencer.toString();
	}

	public void desarDadesBlog(String fitxer) throws FileNotFoundException {
		XMLEncoder e;
		e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fitxer)));

		e.writeObject(usuaris);
		e.writeObject(entrades);

		e.close();
	}

	public void carregarDadesBlog(String fitxer) throws FileNotFoundException {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(fitxer)));

		usuaris.addAll((HashSet<Usuari>) d.readObject());
		entrades.addAll((TreeSet<Entrada>) d.readObject());

		d.close();
	}

	private static Calendar toCalendar(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal;
	}
}
