package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakStudent;

public class TRezervacijaPrimjerakStudent {

	public static ArrayList<MRezervacijaPrimjerakStudent> listaRezervacijaPrimjerakStudent = new ArrayList<>();

	public static ArrayList<MRezervacijaPrimjerakStudent> getListaRezervacijaPrimjerakNastavnik(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MRezervacijaPrimjerakStudent pom = new MRezervacijaPrimjerakStudent();	
			
			pom.setSifRezPrimStud(rs.getInt("sifRezPrimStud"));
			pom.setSifPrimjerak(rs.getInt("sifPrimjerak"));
			pom.setSifRezervacija(rs.getInt("sifRezervacija"));
			pom.setSifStudent(rs.getInt("sifStudent"));
			
			listaRezervacijaPrimjerakStudent.add(pom);

		}

		return listaRezervacijaPrimjerakStudent;
	}
	
}
