package modeli.biblioteka.ba.fet;

import java.sql.Date;

public class MPrimjerak {

	private int sifPrimjerak;
	private String inventartniBr;
	private Date datumNabavke;
	private String stanje;
	private int rezervisan;
	private int sifKnjiga;
	
	public MPrimjerak() {
		this.sifPrimjerak = -1;
		this.inventartniBr = "";
		this.datumNabavke = Date.valueOf("0000-1-1");
		this.stanje = "";
		this.rezervisan = 0;
		this.sifKnjiga = -1;
	}

	public int getSifPrimjerak() {
		return sifPrimjerak;
	}

	public void setSifPrimjerak(int sifPrimjerak) {
		this.sifPrimjerak = sifPrimjerak;
	}

	public String getInventartniBr() {
		return inventartniBr;
	}

	public void setInventartniBr(String inventartniBr) {
		this.inventartniBr = inventartniBr;
	}

	public Date getDatumNabavke() {
		return datumNabavke;
	}

	public void setDatumNabavke(Date datumNabavke) {
		this.datumNabavke = datumNabavke;
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	public int getSifKnjiga() {
		return sifKnjiga;
	}

	public void setSifKnjiga(int sifKnjiga) {
		this.sifKnjiga = sifKnjiga;
	}

	public int getRezervisan() {
		return rezervisan;
	}

	public void setRezervisan(int rezervisan) {
		this.rezervisan = rezervisan;
	}
	
	
	
	
}
