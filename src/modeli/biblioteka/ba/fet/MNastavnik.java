package modeli.biblioteka.ba.fet;

public class MNastavnik {

	private int sifNastavnik;
	private String imeNastavnik;
	private String prezNastavnik;
	private String zvanje;
	private int negBodovi;
	private String password;
	private int bibliotekar;
	private int brPosudjenihKnjiga;
	private int brRezervacija;
	
	public MNastavnik() {
		this.sifNastavnik = -1;
		this.imeNastavnik = "";
		this.prezNastavnik = "";
		this.zvanje = "";
		this.negBodovi = -1;
		this.password = "";
		this.bibliotekar = -1;
		this.brPosudjenihKnjiga = 0;
		this.brRezervacija = 0;
	}

	public int getSifNastavnik() {
		return sifNastavnik;
	}

	public void setSifNastavnik(int sifNastavnik) {
		this.sifNastavnik = sifNastavnik;
	}

	public String getImeNastavnik() {
		return imeNastavnik;
	}

	public void setImeNastavnik(String imeNastavnik) {
		this.imeNastavnik = imeNastavnik;
	}

	public String getPrezNastavnik() {
		return prezNastavnik;
	}

	public void setPrezNastavnik(String prezNastavnik) {
		this.prezNastavnik = prezNastavnik;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public int getNegBodovi() {
		return negBodovi;
	}

	public void setNegBodovi(int negBodovi) {
		this.negBodovi = negBodovi;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBibliotekar() {
		return bibliotekar;
	}

	public void setBibliotekar(int bibliotekar) {
		this.bibliotekar = bibliotekar;
	}

	public int getBrPosudjenihKnjiga() {
		return brPosudjenihKnjiga;
	}

	public void setBrPosudjenihKnjiga(int brPosudjenihKnjiga) {
		this.brPosudjenihKnjiga = brPosudjenihKnjiga;
	}

	public int getBrRezervacija() {
		return brRezervacija;
	}

	public void setBrRezervacija(int brRezervacija) {
		this.brRezervacija = brRezervacija;
	}
	
	
	
}
