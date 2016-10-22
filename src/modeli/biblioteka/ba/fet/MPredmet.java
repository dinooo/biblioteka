package modeli.biblioteka.ba.fet;

public class MPredmet {

	private int sifPredmet;
	private String nazPredmet;
	private String kratPredmet;
	private int sifSemestar;
	private int sifnastavnik;
	
	public MPredmet() {
		this.sifPredmet = -1;
		this.nazPredmet = "";
		this.kratPredmet = "";
		this.sifSemestar = -1;
		this.sifnastavnik = -1;
	}

	public int getSifPredmet() {
		return sifPredmet;
	}

	public void setSifPredmet(int sifPredmet) {
		this.sifPredmet = sifPredmet;
	}

	public String getNazPredmet() {
		return nazPredmet;
	}

	public void setNazPredmet(String nazPredmet) {
		this.nazPredmet = nazPredmet;
	}

	public String getKratPredmet() {
		return kratPredmet;
	}

	public void setKratPredmet(String kratPredmet) {
		this.kratPredmet = kratPredmet;
	}

	public int getSifSemestar() {
		return sifSemestar;
	}

	public void setSifSemestar(int sifSemestar) {
		this.sifSemestar = sifSemestar;
	}

	public int getSifnastavnik() {
		return sifnastavnik;
	}

	public void setSifnastavnik(int sifnastavnik) {
		this.sifnastavnik = sifnastavnik;
	}
	
	
	
}
