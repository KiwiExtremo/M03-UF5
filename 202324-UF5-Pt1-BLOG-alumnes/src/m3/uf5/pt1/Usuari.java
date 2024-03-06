package m3.uf5.pt1;

import java.util.ArrayList;

public class Usuari implements Comparable<Usuari> {
	public static final int JUNIOR_LIMIT = 2;
	public static final int SENIOR_LIMIT = 5;
	private String nick;
	private String mail;
	public ArrayList<Publicacio> publicacions; // TODO check proper collection is used

	public Usuari(String nick, String mail) {
		super();
		this.nick = nick;
		this.mail = mail;
		this.publicacions = new ArrayList<Publicacio>();
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ArrayList<Publicacio> getPublicacions() {
		return publicacions;
	}

	public void setPublicacions(ArrayList<Publicacio> publicacions) {
		this.publicacions = publicacions;
	}

	public void afegirPublicacio(Publicacio publicacio) {
		if (publicacio != null) {
			publicacions.add(publicacio);
		}
	}

	public String nivellUsuari() {
		if (publicacions.size() <= JUNIOR_LIMIT) {
			return "Júnior";
		} else if (publicacions.size() <= SENIOR_LIMIT) {
			return "Sènior";
		} else {
			return "Màster";
		}
	}

	@Override
	public int compareTo(Usuari u) {
		return this.mail.compareTo(u.getMail());
	}
}
