package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MPrimjerak;

public class TPrimjerak {

	public static ArrayList<MPrimjerak> listaPrimjerak = new ArrayList<>();

	public static ArrayList<MPrimjerak> getListaPrimjerak(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MPrimjerak pom = new MPrimjerak();	
			
			pom.setSifPrimjerak(rs.getInt("sifPrimjerak"));
			pom.setInventartniBr(rs.getString("inventarniBr"));
			pom.setDatumNabavke(rs.getDate("datumNabavke"));
			pom.setStanje(rs.getString("stanje"));
			pom.setRezervisan(rs.getInt("rezervisan"));
			pom.setSifKnjiga(rs.getInt("sifKnjiga"));
			
			listaPrimjerak.add(pom);

		}

		return listaPrimjerak;
	}
	
}
