package modeli.biblioteka.ba.fet;

public class MRezervacijaPrimjerakStudent {

	private int sifRezPrimStud;
	private int sifRezervacija;
	private int sifPrimjerak;
	private int sifStudent;
	
	public MRezervacijaPrimjerakStudent() {
		this.sifRezPrimStud = -1;
		this.sifRezervacija = -1;
		this.sifPrimjerak = -1;
		this.sifStudent = -1;
	}

	public int getSifRezPrimStud() {
		return sifRezPrimStud;
	}

	public void setSifRezPrimStud(int sifRezPrimStud) {
		this.sifRezPrimStud = sifRezPrimStud;
	}

	public int getSifRezervacija() {
		return sifRezervacija;
	}

	public void setSifRezervacija(int sifRezervacija) {
		this.sifRezervacija = sifRezervacija;
	}

	public int getSifPrimjerak() {
		return sifPrimjerak;
	}

	public void setSifPrimjerak(int sifPrimjerak) {
		this.sifPrimjerak = sifPrimjerak;
	}

	public int getSifStudent() {
		return sifStudent;
	}

	public void setSifStudent(int sifStudent) {
		this.sifStudent = sifStudent;
	}
	
	
	
}
