package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MStudent;

public class TStudent {

	public static ArrayList<MStudent> listaStudent = new ArrayList<>();
	
	public static ArrayList<MStudent> getListaStudent(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MStudent pom = new MStudent();	
			
			pom.setSifStudent(rs.getInt("sifStudent"));
			pom.setImeStudent(rs.getString("imeStudent"));
			pom.setPrezStudent(rs.getString("prezStudent"));
			pom.setBrIndexa(rs.getString("brIndexa"));
			pom.setNegBodovi(rs.getInt("negBodovi"));
			pom.setPassword(rs.getString("password"));
			pom.setBrPosudjenihKnjiga(rs.getInt("brPosudjenihKnjiga"));
			pom.setSifSemestar(rs.getInt("sifSemestar"));
			pom.setBrRezervacija(rs.getInt("brRezervacija"));
			
			listaStudent.add(pom);

		}

		return listaStudent;
	}
	
}
