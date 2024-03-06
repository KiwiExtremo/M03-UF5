package m3.uf5.pt1;

import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

public class Blog {
	public static final int AMPLE_LEFT = 15;
	public static final int GAP = 3;
	public static final int AMPLE_CONTENT = 60;
	public HashSet<Usuari> usuaris;
	public TreeSet<Entrada> entrades;

	public Blog() {
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

	private Entrada cercarEntradaPerDataTitol(Date data, String titol) {
		if (titol != null) {
			// TODO Retorna una entrada pel dia «data» amb el mateix «titol», sinó la troba
			// retorna nul.
		} else {
			return null;
		}
		return null;
	}

	private Usuari cercarUsuariPerMail(String mail) {
		if (mail != null) {
			// TODO Retorna l'usuari amb aquest «mail», sinó el troba retorna nul.
			return null;
		} else {
			return null;
		}
	}

	private Usuari cercarUsuariPerNick(String nick) {
		if (nick != null) {
			// TODO Retorna l'usuari amb aquest «nick», sinó el troba retorna nul.
			return null;
		} else {
			return null;
		}
	}

	public void nouUsuari(String nick, String mail) {
		// TODO Afegeix un nou usuari al conjunt d'usuaris del blog només si no existeix
		// cap altre usuari amb el mateix «mail» o «nick». En cas contrari llença
		// excepció.
	}

	public void afegirEntrada(String mail, String text, String titol) {
		// TODO Si no existeix cap altre entrada per la data actual amb el mateix
		// «titol», afegeix a una nova entrada de l'usuari amb «mail» amb el «text»
		// indicat. En cas contrari llença excepció.
	}

	public void comentarEntrada(String mail, Date data, String titol, String text, int valoracio) {
		// TODO Si no existeix cap altre entrada per la data actual amb l mateix
		// «titol», afegeix a una nova entrada de l'usuari amb «mail» amb el «text»
		// indicat. En cas contrari llença excepció.

	}

	public String imprimirEntrada(Date data, String titol) {
		// TODO Si existeix una entrada pel dia «data» amb el mateix «titol», retorna el
		// text d’aquesta entrada incloent els comentaris. En cas contrari llença
		// excepció.
		return null;
	}

	public String imprimirBlog() {
		// TODO Retorna un text amb una capçalera i seguidament totes les entrades del
		// Blog ordenadament incloent els seus comentairs. La capçalera és un text
		// centrat on s’indiquen el nombre d’usuaris i entrades del blog, subratllat amb
		// el caràcter "^". L’ample total és: AMPLE_LEFT+GAP+AMPLE_CONTENT.
		return null;
	}

	public void desarDadesBlog(String fitxer) {

	}

	public void carregarDadesBlog(String fitxer) {

	}

}
