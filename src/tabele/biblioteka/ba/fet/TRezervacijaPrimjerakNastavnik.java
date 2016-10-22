package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakNastavnik;

public class TRezervacijaPrimjerakNastavnik {

	public static ArrayList<MRezervacijaPrimjerakNastavnik> listaRezervacijaPrimjerakNastavnik = new ArrayList<>();

	public static ArrayList<MRezervacijaPrimjerakNastavnik> getListaRezervacijaPrimjerakNastavnik(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MRezervacijaPrimjerakNastavnik pom = new MRezervacijaPrimjerakNastavnik();	
			
			pom.setSifRezPrimNast(rs.getInt("sifRezPrimNast"));
			pom.setSifPrimjerak(rs.getInt("sifPrimjerak"));
			pom.setSifRezervacija(rs.getInt("sifRezervacija"));
			pom.setSifNastavnik(rs.getInt("sifNastavnik"));
			
			listaRezervacijaPrimjerakNastavnik.add(pom);

		}

		return listaRezervacijaPrimjerakNastavnik;
	}
	
	
}
