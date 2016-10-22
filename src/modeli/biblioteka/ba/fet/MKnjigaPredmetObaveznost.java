package modeli.biblioteka.ba.fet;

public class MKnjigaPredmetObaveznost {

	private int sifKnjPredObav;
	private int sifVaznObav;
	private int sifKnjiga;
	private int sifPredmet;
	
	public MKnjigaPredmetObaveznost() {
		this.sifKnjPredObav = -1;
		this.sifVaznObav = -1;
		this.sifKnjiga = -1;
		this.sifPredmet = -1;
	}

	public int getSifKnjPredObav() {
		return sifKnjPredObav;
	}

	public void setSifKnjPredObav(int sifKnjPredObav) {
		this.sifKnjPredObav = sifKnjPredObav;
	}

	public int getSifVaznObav() {
		return sifVaznObav;
	}

	public void setSifVaznObav(int sifVaznObav) {
		this.sifVaznObav = sifVaznObav;
	}

	public int getSifKnjiga() {
		return sifKnjiga;
	}

	public void setSifKnjiga(int sifKnjiga) {
		this.sifKnjiga = sifKnjiga;
	}

	public int getSifPredmet() {
		return sifPredmet;
	}

	public void setSifPredmet(int sifPredmet) {
		this.sifPredmet = sifPredmet;
	}
	
	
	
}
