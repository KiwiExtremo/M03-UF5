package m3.uf5.pt1;

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
		StringBuilder comentariImprimible = new StringBuilder();
		
		String textWrapper = WordUtils.wrap(text, Blog.AMPLE_CONTENT, System.lineSeparator(), false);
		
		String[] textEnLinies = textWrapper.split(System.lineSeparator());
		
		int linies = textEnLinies.length - 1;
		
		comentariImprimible.append(StringUtils.rightPad(data.toString(), Blog.AMPLE_LEFT, " "));
		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.center(titol, Blog.AMPLE_CONTENT));
		comentariImprimible.append(System.lineSeparator());
		
		comentariImprimible.append(StringUtils.rightPad(usuari.getNick() + " lv:" + usuari.nivellUsuari(), Blog.AMPLE_LEFT, " "));
		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.center(StringUtils.repeat("-", titol.length()), Blog.AMPLE_CONTENT));
		comentariImprimible.append(System.lineSeparator());
		
		comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_CONTENT));
		comentariImprimible.append(System.lineSeparator());
		
		for (int i = 0; i < 4; i++) {
			comentariImprimible.append(StringUtils.rightPad(Comentari.getTextValoracio(i) + " : " + totalValoracionsPerValor(0), Blog.AMPLE_LEFT));
			comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
			comentariImprimible.append(StringUtils.rightPad(linies >= i ? textEnLinies[i] : " ", Blog.AMPLE_CONTENT));
			comentariImprimible.append(System.lineSeparator());
		}
//		comentariImprimible.append(StringUtils.rightPad(Comentari.getTextValoracio(0) + " : " + totalValoracionsPerValor(0), Blog.AMPLE_LEFT));
//		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
//		comentariImprimible.append(StringUtils.rightPad(linies >= 0 ? textEnLinies[0] : " ", Blog.AMPLE_CONTENT));
//		comentariImprimible.append(System.lineSeparator());
//		
//		comentariImprimible.append(StringUtils.rightPad(Comentari.getTextValoracio(1) + "  : " + totalValoracionsPerValor(1), Blog.AMPLE_LEFT));
//		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
//		comentariImprimible.append(StringUtils.rightPad(linies >= 1 ? textEnLinies[1] : " ", Blog.AMPLE_CONTENT));
//		comentariImprimible.append(System.lineSeparator());
//		
//		comentariImprimible.append(StringUtils.rightPad(Comentari.getTextValoracio(2) + " : " + totalValoracionsPerValor(2), Blog.AMPLE_LEFT));
//		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
//		comentariImprimible.append(StringUtils.rightPad(linies >= 2 ? textEnLinies[2] : " ", Blog.AMPLE_CONTENT));
//		comentariImprimible.append(System.lineSeparator());
//		
//		comentariImprimible.append(StringUtils.rightPad(Comentari.getTextValoracio(3) + " : " + totalValoracionsPerValor(3), Blog.AMPLE_LEFT));
//		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
//		comentariImprimible.append(StringUtils.rightPad(linies >= 3 ? textEnLinies[3] : " ", Blog.AMPLE_CONTENT));
//		comentariImprimible.append(System.lineSeparator());

		comentariImprimible.append(StringUtils.rightPad("Mitjana : " + valoracioMitjaEntrada(), Blog.AMPLE_LEFT));
		comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.rightPad(linies >= 4 ? textEnLinies[4] : " ", Blog.AMPLE_CONTENT));
		comentariImprimible.append(System.lineSeparator());
		
		for (int i = 5; i < linies; i++) {
			comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
			comentariImprimible.append(StringUtils.center(SEPARADOR, Blog.GAP));
			comentariImprimible.append(StringUtils.rightPad(textEnLinies[i], Blog.AMPLE_CONTENT));
			comentariImprimible.append(System.lineSeparator());
		}

		Iterator<Comentari> it = comentaris.iterator();
		
		while (it.hasNext()) {
			Comentari comentari = (Comentari) it.next();
			
			comentariImprimible.append(comentari.imprimirPublicacio(prefix, width));
			comentariImprimible.append(System.lineSeparator());
		}
		
		return comentariImprimible.toString();
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
