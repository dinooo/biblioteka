package modeli.biblioteka.ba.fet;

/*
 * Klasa ModelAutor, kao i sve ostale klase koje pocinju sa "Model..." treba da budu kalup za kreiranje zapisa u BP za odgovarajucu
 * tabelu. Polja ovih klasa odgovaraju kolonama tabela iz BP. Kreiran je default konstruktor, kao i getteri i setteri za svaki model.
 */

public class MAutor {
	
	private int sifAutor;
	private String imeAutor;
	private String prezAutor;


	public MAutor() {
		this.sifAutor = -1;
		this.imeAutor = "";
		this.prezAutor = "";
	}

	public int getSifAutor() {
		return sifAutor;
	}

	public void setSifAutor(int sifAutor) {
		this.sifAutor = sifAutor;
	}

	public String getImeAutor() {
		return imeAutor;
	}

	public void setImeAutor(String imeAutor) {
		this.imeAutor = imeAutor;
	}

	public String getPrezAutor() {
		return prezAutor;
	}

	public void setPrezAutor(String prezAutor) {
		this.prezAutor = prezAutor;
	}

}
