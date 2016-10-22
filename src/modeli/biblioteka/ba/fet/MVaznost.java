package modeli.biblioteka.ba.fet;

public class MVaznost {

	private int sifVaznost;
	private int rBrVaznost;
	
	public MVaznost(){
		this.sifVaznost = -1;
		this.rBrVaznost = -1;
	}

	public int getSifVaznost() {
		return sifVaznost;
	}

	public void setSifVaznost(int sifVaznost) {
		this.sifVaznost = sifVaznost;
	}

	public int getrBrVaznost() {
		return rBrVaznost;
	}

	public void setrBrVaznost(int rBrVaznost) {
		this.rBrVaznost = rBrVaznost;
	}
	
}
