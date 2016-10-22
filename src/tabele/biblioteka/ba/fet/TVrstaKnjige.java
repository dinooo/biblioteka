package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MVrstaKnjige;

public class TVrstaKnjige {

	public static ArrayList<MVrstaKnjige> listaVrstaKnjige = new ArrayList<>();

	public static ArrayList<MVrstaKnjige> getListaVrstaKnjige(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MVrstaKnjige pom = new MVrstaKnjige();	

			pom.setSifVrstaKnjige(rs.getInt("sifVrstaKnjige"));
			pom.setVrsta(rs.getString("vrsta"));


			listaVrstaKnjige.add(pom);

		}

		return listaVrstaKnjige;
	}
	
}
