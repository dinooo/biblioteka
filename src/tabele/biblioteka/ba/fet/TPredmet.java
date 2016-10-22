package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MPredmet;

public class TPredmet {

	public static ArrayList<MPredmet> listaPredmet = new ArrayList<>();

	public static ArrayList<MPredmet> getListaPredmet(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MPredmet pom = new MPredmet();	
			
			pom.setSifPredmet(rs.getInt("sifPredmet"));
			pom.setNazPredmet(rs.getString("nazPredmet"));
			pom.setKratPredmet(rs.getString("kratPredmet"));
			pom.setSifSemestar(rs.getInt("sifSemestar"));
			pom.setSifnastavnik(rs.getInt("sifNastavnik"));
			
			listaPredmet.add(pom);

		}

		return listaPredmet;
	}

}
