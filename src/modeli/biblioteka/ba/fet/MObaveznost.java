package modeli.biblioteka.ba.fet;

public class MObaveznost {

	private int sifObaveznost;
	private String obavezna;
	
	public MObaveznost(){
		this.sifObaveznost = -1;
		this.obavezna = "";
	}

	public int getSifObaveznost() {
		return sifObaveznost;
	}

	public void setSifObaveznost(int sifObaveznost) {
		this.sifObaveznost = sifObaveznost;
	}

	public String getObavezna() {
		return obavezna;
	}

	public void setObavezna(String obavezna) {
		this.obavezna = obavezna;
	}


	
}
