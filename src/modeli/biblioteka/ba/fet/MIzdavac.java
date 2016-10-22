package modeli.biblioteka.ba.fet;

public class MIzdavac {
	
	private int sifIzdavac;
	private String nazIzdavac;
	
	public MIzdavac() {
		this.sifIzdavac = -1;
		this.nazIzdavac = "";
	}

	public int getSifIzdavac() {
		return sifIzdavac;
	}

	public void setSifIzdavac(int sifIzdavac) {
		this.sifIzdavac = sifIzdavac;
	}

	public String getNazIzdavac() {
		return nazIzdavac;
	}

	public void setNazIzdavac(String nazIzdavac) {
		this.nazIzdavac = nazIzdavac;
	}
}
