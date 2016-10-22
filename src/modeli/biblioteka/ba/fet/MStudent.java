package modeli.biblioteka.ba.fet;

public class MStudent {

	private int sifStudent;
	private String imeStudent;
	private String prezStudent;
	private String brIndexa;
	private int negBodovi;
	private String password;
	private int sifSemestar;
	private int brPosudjenihKnjiga;
	int brRezervacija;
	
	public MStudent(){
		this.sifStudent = -1;
		this.imeStudent = "";
		this.prezStudent = "";
		this.brIndexa = "";
		this.negBodovi = -1;
		this.password = "";
		this.sifSemestar = -1;
		this.brPosudjenihKnjiga = 0;
		this.brRezervacija = 0;
	}

	public int getBrPosudjenihKnjiga() {
		return brPosudjenihKnjiga;
	}

	public void setBrPosudjenihKnjiga(int brPosudjenihKnjiga) {
		this.brPosudjenihKnjiga = brPosudjenihKnjiga;
	}

	public int getSifStudent() {
		return sifStudent;
	}

	public void setSifStudent(int sifStudent) {
		this.sifStudent = sifStudent;
	}

	public String getImeStudent() {
		return imeStudent;
	}

	public void setImeStudent(String imeStudent) {
		this.imeStudent = imeStudent;
	}

	public String getPrezStudent() {
		return prezStudent;
	}

	public void setPrezStudent(String prezStudent) {
		this.prezStudent = prezStudent;
	}

	public String getBrIndexa() {
		return brIndexa;
	}

	public void setBrIndexa(String brIndexa) {
		this.brIndexa = brIndexa;
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

	public int getSifSemestar() {
		return sifSemestar;
	}

	public void setSifSemestar(int sifSemestar) {
		this.sifSemestar = sifSemestar;
	}

	public int getBrRezervacija() {
		return brRezervacija;
	}

	public void setBrRezervacija(int brRezervacija) {
		this.brRezervacija = brRezervacija;
	}
	
	
	
}
