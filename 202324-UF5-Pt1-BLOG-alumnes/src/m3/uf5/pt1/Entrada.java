package m3.uf5.pt1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class Entrada extends Publicacio implements Comparable<Entrada> {
	public static final String SEPARADOR = "|";
	public static final String NOT_PROVIDED = "NA";
	private String titol;
	private LinkedList<Comentari> comentaris;

	public Entrada(Usuari usuari, String titol, String text) {
		super(usuari, text);
		this.titol = titol;
		this.comentaris = new LinkedList<>();
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public LinkedList<Comentari> getComentaris() {
		return comentaris;
	}

	public void setComentaris(LinkedList<Comentari> comentaris) {
		this.comentaris = comentaris;
	}

	public void afegirComentari(Usuari usuari, String text, int valoracio) throws Exception {
		Comentari com = new Comentari(usuari, text, valoracio);

		comentaris.addFirst(com);
	}

	public String valoracioMitjaEntrada() {
		if (comentaris.isEmpty()) {
			return NOT_PROVIDED;
		}

		float mitjana, sumatori = 0;

		for (Comentari comentari : comentaris) {
			sumatori = sumatori + comentari.getValoracio();
		}

		mitjana = sumatori / comentaris.size();

		return String.format("%.1f", mitjana);
	}

	public int totalValoracionsPerValor(int valor) {
		int sumatori = 0;

		for (Comentari comentari : comentaris) {
			if (comentari.getValoracio() == valor) {
				sumatori++;
			}
		}
		return sumatori;
	}

	@Override
	public String imprimirPublicacio(String prefix, int width) {
		// Formatejar la data de l'entrada
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM yyyy");
		String dataFormatada = sdf.format(data);
		
		// Separar el cos de l'entrada en línies
		String textWrapper = WordUtils.wrap(text, Blog.AMPLE_CONTENT, System.lineSeparator(), false);
		String[] textEnLinies = textWrapper.split(System.lineSeparator());
		
		int linies = textEnLinies.length - 1;
		
		String entrada = "";
		
		// Titol de l'entrada
		entrada += StringUtils.rightPad(dataFormatada.toUpperCase(), Blog.AMPLE_LEFT, " ");
		entrada += StringUtils.center(SEPARADOR, Blog.GAP);
		entrada += StringUtils.center(titol, Blog.AMPLE_CONTENT);
		entrada += System.lineSeparator();
		
		entrada += StringUtils.rightPad(usuari.getNick() + " lv:" + usuari.nivellUsuari(), Blog.AMPLE_LEFT, " ");
		entrada += StringUtils.center(SEPARADOR, Blog.GAP);
		entrada += StringUtils.center(StringUtils.repeat("-", titol.length()), Blog.AMPLE_CONTENT);
		entrada += System.lineSeparator();
		
		entrada += StringUtils.repeat(" ", Blog.AMPLE_LEFT);
		entrada += StringUtils.center(SEPARADOR, Blog.GAP);
		entrada += StringUtils.repeat(" ", Blog.AMPLE_CONTENT);
		entrada += System.lineSeparator();
		
		// Cos de l'entrada i puntuacions a l'esquerra
		for (int i = 0; i < linies; i++) {
			// Comprovar què s'ha d'imprimir a l'esquerra
			if (i == 1) { // Afegir un (1) espai darrere de "1-star" per alinear-ho
				entrada += StringUtils.rightPad((Comentari.getTextValoracio(i)  + " ") + " : " + totalValoracionsPerValor(0), Blog.AMPLE_LEFT);
			
			} else if (i == 4){ // Afejir la mitjana
				entrada += StringUtils.rightPad("Mitjana : " + valoracioMitjaEntrada(), Blog.AMPLE_LEFT);
			
			} else if (i >= 0 && i < 4) { // Afegir les puntuacions
				entrada += StringUtils.rightPad(Comentari.getTextValoracio(i) + " : " + totalValoracionsPerValor(0), Blog.AMPLE_LEFT);

			} else {
				entrada += StringUtils.repeat(" ", Blog.AMPLE_LEFT);

			}
			// Imprimir cos de l'entrada
			entrada += StringUtils.center(SEPARADOR, Blog.GAP);
			entrada += StringUtils.rightPad(linies >= i ? textEnLinies[i] : " ", Blog.AMPLE_CONTENT);
			entrada += System.lineSeparator();
		}

		entrada += StringUtils.repeat(" ", Blog.AMPLE_LEFT);
		entrada += StringUtils.center(SEPARADOR, Blog.GAP);
		entrada += StringUtils.repeat(" ", Blog.AMPLE_CONTENT);
		entrada += System.lineSeparator();
		
		// Imprimir comentaris de l'entrada si s'escau
		Iterator<Comentari> it = comentaris.iterator();
		
		while (it.hasNext()) {
			Comentari comentari = (Comentari) it.next();
			
			entrada += comentari.imprimirPublicacio(prefix, width);
		}
		
		return entrada;
	}

	@Override
	public int compareTo(Entrada e) {
		if (toCalendar(data).compareTo(toCalendar(e.getData())) == 0) {
			return this.titol.compareTo(e.getTitol());
		}

		return this.data.compareTo(e.getData());
	}
	
	private static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.HOUR_OF_DAY, 0);
		  cal.set(Calendar.MINUTE, 0);
		  cal.set(Calendar.SECOND, 0);
		  cal.set(Calendar.MILLISECOND, 0);
		  
		  return cal;
	}
}
