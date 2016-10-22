package modeli.biblioteka.ba.fet;

public class MVrstaKnjige {

	private int sifVrstaKnjige;
	private String vrsta;
	
	public MVrstaKnjige(){
		this.sifVrstaKnjige = -1;
		this.vrsta = "";
	}

	public int getSifVrstaKnjige() {
		return sifVrstaKnjige;
	}

	public void setSifVrstaKnjige(int sifVrstaKnjige) {
		this.sifVrstaKnjige = sifVrstaKnjige;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	
	
	
}
