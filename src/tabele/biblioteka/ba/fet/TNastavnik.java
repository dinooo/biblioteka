package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MNastavnik;

public class TNastavnik {

	public static ArrayList<MNastavnik> listaNastavnik = new ArrayList<>();

	public static ArrayList<MNastavnik> getListaNastavnik(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MNastavnik pom = new MNastavnik();	
			
			pom.setSifNastavnik(rs.getInt("sifNastavnik"));
			pom.setImeNastavnik(rs.getString("imeNastavnik"));
			pom.setPrezNastavnik(rs.getString("prezNastavnik"));
			pom.setZvanje(rs.getString("zvanje"));
			pom.setNegBodovi(rs.getInt("negBodovi"));
			pom.setPassword(rs.getString("password"));
			pom.setBibliotekar(rs.getInt("bibliotekar"));
			pom.setBrPosudjenihKnjiga(rs.getInt("brPosudjenihKnjiga"));
			pom.setBrRezervacija(rs.getInt("brRezervacija"));
			
			listaNastavnik.add(pom);

		}

		return listaNastavnik;
	}

}
