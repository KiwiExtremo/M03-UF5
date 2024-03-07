package m3.uf5.pt1;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class Comentari extends Publicacio {
	public static final int IDENT_COMMENT = 5;
	public static final int IDENT_INC = 2;
	private int valoracio;
	private static HashMap<Integer, String> valoracions = new HashMap<>();
	static {
		valoracions = new HashMap<>();
		valoracions.put(0, "0-stars");
		valoracions.put(1, "1-star");
		valoracions.put(2, "2-stars");
		valoracions.put(3, "3-stars");
	}

	public Comentari(Usuari usuari, String text, int valoracio) throws Exception {
		super(usuari, text);

		if (!containsValoracio(valoracio)) {
			throw new Exception("La valoració ha de ser entre 0 i 3 estrelles.");

		} else {
			this.valoracio = valoracio;
		}
	}

	public int getValoracio() {
		return valoracio;
	}

	public void setValoracio(int valoracio) throws Exception {
		if (!containsValoracio(valoracio)) {
			throw new Exception("La valoració ha de ser entre 0 i 3 estrelles.");

		} else {
			this.valoracio = valoracio;
		}
	}

	public static HashMap<Integer, String> getValoracions() {
		return valoracions;
	}

	public static void setValoracions(HashMap<Integer, String> valoracions) {
		Comentari.valoracions = valoracions;
	}

	public static boolean containsValoracio(int key) {
		return valoracions.containsKey(key);
	}

	public static String getTextValoracio(int key) {
		return valoracions.get(key);
	}

	@Override
	public String imprimirPublicacio(String prefix, int width) {
		StringBuilder comentariImprimible = new StringBuilder();
		
		String textWrapper = WordUtils.wrap(text, Blog.AMPLE_CONTENT - IDENT_COMMENT - IDENT_INC, System.lineSeparator(), false);
		
		String[] textEnLinies = textWrapper.split(System.lineSeparator());
		
		comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
		comentariImprimible.append(StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.repeat(" ", IDENT_COMMENT + IDENT_INC));
		comentariImprimible.append(StringUtils.center(usuari.getNick() + ".- \"" + textEnLinies[0], Blog.AMPLE_CONTENT - (IDENT_COMMENT + IDENT_INC)));
		comentariImprimible.append(System.lineSeparator());
		
		for (int i = 1; i < textEnLinies.length; i++) {
			comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
			comentariImprimible.append(StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
			comentariImprimible.append(StringUtils.repeat(" ", IDENT_COMMENT + IDENT_INC));
			comentariImprimible.append(StringUtils.center(IDENT_COMMENT + IDENT_INC + textEnLinies[i] + (i == textEnLinies.length - 1 ? "\"" : ""), Blog.AMPLE_CONTENT - (IDENT_COMMENT + IDENT_INC)));
		}
		comentariImprimible.append(System.lineSeparator());
		
		comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
		comentariImprimible.append(StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.repeat(" ", IDENT_COMMENT + IDENT_INC));
		comentariImprimible.append(StringUtils.center(IDENT_COMMENT + IDENT_INC + data.toString() + ", valoració: " + getTextValoracio(getValoracio()), Blog.AMPLE_CONTENT - (IDENT_COMMENT + IDENT_INC)));
		comentariImprimible.append(System.lineSeparator());
		
		comentariImprimible.append(StringUtils.repeat(" ", Blog.AMPLE_LEFT));
		comentariImprimible.append(StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
		comentariImprimible.append(StringUtils.leftPad("-----", Blog.AMPLE_CONTENT, " "));
		comentariImprimible.append(System.lineSeparator());
		
		return comentariImprimible.toString();
	}
}
