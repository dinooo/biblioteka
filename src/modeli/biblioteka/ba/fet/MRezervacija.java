package modeli.biblioteka.ba.fet;

import java.sql.Date;

public class MRezervacija {

	private int sifRezervacija;
	private Date datRezervacija;
	private Date datPodizanja;
	private Date datVracanja;
	private Date datKadVracena;
	private int sifKorisnik;
	private int sifPrimjerak;
	private int nastStud;
	private int odobrena;
	
	public MRezervacija() {
		this.sifRezervacija = -1;
		this.datRezervacija = Date.valueOf("0000-1-1");
		this.datPodizanja = null;
		this.datKadVracena = null;
		this.datVracanja = Date.valueOf("0000-1-1");
		this.sifKorisnik = -1;
		this.sifPrimjerak = -1;
		this.nastStud = -1;
		this.odobrena = 0;
	}

	public int getSifRezervacija() {
		return sifRezervacija;
	}

	public void setSifRezervacija(int sifRezervacija) {
		this.sifRezervacija = sifRezervacija;
	}

	public Date getDatRezervacija() {
		return datRezervacija;
	}

	public void setDatRezervacija(Date datRezervacija) {
		this.datRezervacija = datRezervacija;
	}

	public Date getDatVracanja() {
		return datVracanja;
	}

	public void setDatVracanja(Date datVracena) {
		this.datVracanja = datVracena;
	}

	public int getSifKorisnik() {
		return sifKorisnik;
	}

	public void setSifKorisnik(int sifKorisnik) {
		this.sifKorisnik = sifKorisnik;
	}

	public int getNastStud() {
		return nastStud;
	}

	public void setNastStud(int nastStud) {
		this.nastStud = nastStud;
	}

	public int getOdobrena() {
		return odobrena;
	}

	public void setOdobrena(int odobrena) {
		this.odobrena = odobrena;
	}

	public Date getDatPodizanja() {
		return datPodizanja;
	}

	public void setDatPodizanja(Date datPodizanja) {
		this.datPodizanja = datPodizanja;
	}

	public Date getDatKadVracena() {
		return datKadVracena;
	}

	public void setDatKadVracena(Date datKadVracena) {
		this.datKadVracena = datKadVracena;
	}

	public int getSifPrimjerak() {
		return sifPrimjerak;
	}

	public void setSifPrimjerak(int sifPrimjerak) {
		this.sifPrimjerak = sifPrimjerak;
	}
	
	
	
}
