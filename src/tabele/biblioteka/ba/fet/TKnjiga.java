package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MKnjiga;

public class TKnjiga {

	public static ArrayList<MKnjiga> listaKnjiga = new ArrayList<>();

	public static ArrayList<MKnjiga> getListaKnjiga(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MKnjiga pom = new MKnjiga();	

			pom.setSifKnjiga(rs.getInt("sifKnjiga"));
			pom.setNaslov(rs.getString("naslov"));
			pom.setOrigNaslov(rs.getString("origNaslov"));
			pom.setBrStranica(rs.getInt("brStranica"));
			pom.setGodIzdanja(rs.getDate("godIzdanja"));
			pom.setNegBodovi(rs.getInt("negBodovi"));
			pom.setBrPrimjeraka(rs.getInt("brPrimjeraka"));
			pom.setSifIzdavac(rs.getInt("sifIzdavac"));
			pom.setSifVrstaKnjige(rs.getInt("sifVrstaKnjige"));


			listaKnjiga.add(pom);

		}

		return listaKnjiga;
	}



}
