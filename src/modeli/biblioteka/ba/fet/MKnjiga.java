package modeli.biblioteka.ba.fet;

import java.sql.Date;

public class MKnjiga {

	private int sifKnjiga;
	private String naslov;
	private String origNaslov;
	private int brStranica;
	private Date godIzdanja;
	private int negBodovi;
	private int brPrimjeraka;
	private int sifIzdavac;
	private int sifVrstaKnjige;
	
	public MKnjiga() {
		this.sifKnjiga = -1;
		this.naslov = "";
		this.origNaslov = "";
		this.brStranica = -1;
		this.godIzdanja = Date.valueOf("0000-1-1");
		this.negBodovi = -1;
		this.brPrimjeraka = -1;
		this.sifIzdavac = -1;
		this.sifVrstaKnjige = -1;
	}

	public int getSifKnjiga() {
		return sifKnjiga;
	}

	public void setSifKnjiga(int sifKnjiga) {
		this.sifKnjiga = sifKnjiga;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOrigNaslov() {
		return origNaslov;
	}

	public void setOrigNaslov(String origNaslov) {
		this.origNaslov = origNaslov;
	}

	public int getBrStranica() {
		return brStranica;
	}

	public void setBrStranica(int brStranica) {
		this.brStranica = brStranica;
	}

	public Date getGodIzdanja() {
		return godIzdanja;
	}

	public void setGodIzdanja(Date godIzdanja) {
		this.godIzdanja = godIzdanja;
	}

	public int getNegBodovi() {
		return negBodovi;
	}

	public void setNegBodovi(int negBodovi) {
		this.negBodovi = negBodovi;
	}

	public int getBrPrimjeraka() {
		return brPrimjeraka;
	}

	public void setBrPrimjeraka(int brPrimjeraka) {
		this.brPrimjeraka = brPrimjeraka;
	}

	public int getSifIzdavac() {
		return sifIzdavac;
	}

	public void setSifIzdavac(int sifIzdavac) {
		this.sifIzdavac = sifIzdavac;
	}

	public int getSifVrstaKnjige() {
		return sifVrstaKnjige;
	}

	public void setSifVrstaKnjige(int sifVrstaKnjige) {
		this.sifVrstaKnjige = sifVrstaKnjige;
	}
	
}
