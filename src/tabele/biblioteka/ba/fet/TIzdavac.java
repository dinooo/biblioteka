package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MIzdavac;

public class TIzdavac {

	public static ArrayList<MIzdavac> listaIzdavaca = new ArrayList<>();

	public static ArrayList<MIzdavac> getListaIzdavaca(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MIzdavac pom = new MIzdavac();	

			pom.setSifIzdavac(rs.getInt("sifIzdavac"));
			pom.setNazIzdavac(rs.getString("nazIzdavac"));

			listaIzdavaca.add(pom);

		}

		return listaIzdavaca;
	}

}
