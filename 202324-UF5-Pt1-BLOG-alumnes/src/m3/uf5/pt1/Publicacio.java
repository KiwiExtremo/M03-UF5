package m3.uf5.pt1;

import java.io.Serializable;
import java.util.Date;

public abstract class Publicacio implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Usuari usuari;
	protected String text;
	protected Date data;

	public Publicacio() {

	}

	public Publicacio(Usuari usuari, String text) {
		super();
		this.usuari = usuari;
		this.text = text;
		this.data = new Date();

		usuari.afegirPublicacio(this);
	}

	public Usuari getUsuari() {
		return usuari;
	}

	public void setUsuari(Usuari usuari) {
		this.usuari = usuari;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public abstract String imprimirPublicacio(String prefix, int width);
}
