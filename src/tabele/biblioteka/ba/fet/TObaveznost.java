package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MObaveznost;

public class TObaveznost {

	public static ArrayList<MObaveznost> listaObaveznost = new ArrayList<>();

	public static ArrayList<MObaveznost> getListaObaveznost(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MObaveznost pom = new MObaveznost();	

			pom.setSifObaveznost(rs.getInt("sifObaveznost"));
			pom.setObavezna(rs.getString("obavezna"));

			listaObaveznost.add(pom);

		}

		return listaObaveznost;
	}

}
