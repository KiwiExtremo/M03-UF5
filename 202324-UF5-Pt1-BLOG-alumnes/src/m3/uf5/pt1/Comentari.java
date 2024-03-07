package m3.uf5.pt1;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class Comentari extends Publicacio {
	private static final long serialVersionUID = 1L;

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

	public Comentari() {
		super();
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
		// Formatejar la data del comentari
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String dataFormatada = sdf.format(data);

		// Separar el cos del comentari en línies
		String textWrapper = WordUtils.wrap((usuari.getNick() + ".- \"" + text + "\""), Blog.AMPLE_CONTENT - width,
				System.lineSeparator(), false);
		String[] textEnLinies = textWrapper.split(System.lineSeparator());

		String comentari = "";

		// Cos del comentari
		for (int i = 0; i < textEnLinies.length; i++) {
			comentari += (StringUtils.repeat(" ", Blog.AMPLE_LEFT));
			comentari += (StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
			comentari += (StringUtils.repeat(" ", IDENT_COMMENT + width));
			comentari += (StringUtils.leftPad(textEnLinies[i], Blog.AMPLE_CONTENT - width, prefix));
			comentari += (System.lineSeparator());
		}

		// Data i valoració del comentari
		comentari += (StringUtils.repeat(" ", Blog.AMPLE_LEFT));
		comentari += (StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
		comentari += (StringUtils.repeat(" ", IDENT_COMMENT + width));
		comentari += (StringUtils.leftPad(dataFormatada + ", valoració: " + getTextValoracio(getValoracio()),
				Blog.AMPLE_CONTENT - width, prefix));
		comentari += (System.lineSeparator());

		// Separador de comentaris
		comentari += (StringUtils.repeat(" ", Blog.AMPLE_LEFT));
		comentari += (StringUtils.center(Entrada.SEPARADOR, Blog.GAP));
		comentari += (StringUtils.leftPad("-----", Blog.AMPLE_CONTENT, " "));
		comentari += (System.lineSeparator());

		return comentari.toString();
	}
}
