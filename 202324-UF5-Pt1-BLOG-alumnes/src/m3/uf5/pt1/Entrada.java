package m3.uf5.pt1;

import java.util.LinkedList;

public class Entrada extends Publicacio implements Comparable<Entrada> {
	public static final String SEPARADOR = "|";
	public static final String NOT_PROVIDED = "NA";
	private String titol;
	private LinkedList<Comentari> comentaris;

	public Entrada(Usuari usuari, String titol, String text) {
		super(usuari, text);
		this.titol = titol;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Entrada e) {
		if (this.data.compareTo(e.getData()) == 0) {
			return this.titol.compareTo(e.getTitol());
		}
		return this.data.compareTo(e.getData());
	}
}
