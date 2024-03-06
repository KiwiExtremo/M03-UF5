package m3.uf5.pt1;

import java.util.HashMap;

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
			throw new Exception("");

		} else {
			this.valoracio = valoracio;
		}
	}

	public int getValoracio() {
		return valoracio;
	}

	public void setValoracio(int valoracio) throws Exception {
		if (!containsValoracio(valoracio)) {
			throw new Exception("");

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
		return null;
	}

	@Override
	public String imprimirPublicacio(String prefix, int width) {
		String comentari = "";

		return null;
	}
}
