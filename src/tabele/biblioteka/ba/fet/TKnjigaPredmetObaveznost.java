package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MKnjigaPredmetObaveznost;


public class TKnjigaPredmetObaveznost {

	public static ArrayList<MKnjigaPredmetObaveznost> listaKnjigaPredmetObaveznost = new ArrayList<>();

	public static ArrayList<MKnjigaPredmetObaveznost> getListaKnjigaPredmetObaveznost(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MKnjigaPredmetObaveznost pom = new MKnjigaPredmetObaveznost();	

			pom.setSifKnjPredObav(rs.getInt("sifKnjPredObav"));
			pom.setSifVaznObav(rs.getInt("sifVaznObav"));
			pom.setSifKnjiga(rs.getInt("sifKnjiga"));
			pom.setSifPredmet(rs.getInt("sifPredmet"));

			listaKnjigaPredmetObaveznost.add(pom);

		}

		return listaKnjigaPredmetObaveznost;
	}

	
}
