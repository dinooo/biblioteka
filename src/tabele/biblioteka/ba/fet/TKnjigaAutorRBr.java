package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MKnjigaAutorRBr;

public class TKnjigaAutorRBr {
	

	public static ArrayList<MKnjigaAutorRBr> ListaKnjigaAutorRBr = new ArrayList<>();

	public static ArrayList<MKnjigaAutorRBr> getListaKnjigaAutor(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MKnjigaAutorRBr pom = new MKnjigaAutorRBr();	

			pom.setSifKnjigaAutorRBr(rs.getInt("sifKnjigaAutorRBr"));
			pom.setSifKnjiga(rs.getInt("sifKnjiga"));
			pom.setSifAutor(rs.getInt("sifAutor"));
			pom.setSifAutorRBr(rs.getInt("sifAutorRBr"));
			ListaKnjigaAutorRBr.add(pom);

		}

		return ListaKnjigaAutorRBr;
	}



}
