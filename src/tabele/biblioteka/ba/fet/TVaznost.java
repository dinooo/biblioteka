package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MVaznost;

public class TVaznost {

	public static ArrayList<MVaznost> listaVaznost = new ArrayList<>();

	public static ArrayList<MVaznost> getListaVaznost(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MVaznost pom = new MVaznost();	

			pom.setSifVaznost(rs.getInt("sifVaznost"));
			pom.setrBrVaznost(rs.getInt("rBrVaznost"));

			listaVaznost.add(pom);

		}

		return listaVaznost;
	}
	
}
