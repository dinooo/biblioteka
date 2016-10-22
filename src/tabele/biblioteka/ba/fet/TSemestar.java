package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MSemestar;

public class TSemestar {
	
	public static ArrayList<MSemestar> listaSemestar = new ArrayList<>();

	public static ArrayList<MSemestar> getListaSemestar(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MSemestar pom = new MSemestar();	
			
			pom.setSifSemestar(rs.getInt("sifSemestar"));
			pom.setSemestar(rs.getString("semestar"));
			
			listaSemestar.add(pom);

		}

		return listaSemestar;
	}
	
}
