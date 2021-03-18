package asseco.beans;

import java.io.Serializable;

import asseco.dao.KorisniciDAO;
import asseco.dto.Korisnici;
import java.util.ArrayList;

public class KorisniciBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Korisnici korisnik = null;
	private boolean isLoggedIn = false;
	
	public Korisnici getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnici korisnik) {
		this.korisnik = korisnik;
	}
	public boolean isLogedIn() {
		return isLoggedIn;
	}
	public void setLogedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean login (String username, String password) {
		korisnik = KorisniciDAO.login(username, password);
		if (korisnik != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}
	
	public ArrayList<String> sviKorisnici(){
		return KorisniciDAO.sviKorisnici();
	}
	
	public void insertKorisnik(String username, String imeIPrezime, int admin) {
		KorisniciDAO.insertKorisnik(username, imeIPrezime, admin);
		
	}

	
}
