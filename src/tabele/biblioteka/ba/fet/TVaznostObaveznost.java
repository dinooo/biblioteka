package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MVaznostObaveznost;

public class TVaznostObaveznost {

	public static ArrayList<MVaznostObaveznost> listaVaznostObaveznost = new ArrayList<>();

	public static ArrayList<MVaznostObaveznost> getListaVazObav(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MVaznostObaveznost pom = new MVaznostObaveznost();	
			
			pom.setSifVaznObav(rs.getInt("sifVaznObav"));
			pom.setSifVaznost(rs.getInt("sifVaznost"));
			pom.setSifObaveznost(rs.getInt("sifObaveznost"));
			
			listaVaznostObaveznost.add(pom);

		}

		return listaVaznostObaveznost;
	}
	
}
