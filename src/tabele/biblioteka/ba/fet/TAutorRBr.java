package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MAutorRBr;

public class TAutorRBr {

	
public static ArrayList<MAutorRBr> listaAutoraRBr = new ArrayList<>();
	
	/*
	 * Sljedeci metod iz tabele Autor iz BP kupi sve autore i snima u iznad kreiranu listu autora
	 */
	public static ArrayList<MAutorRBr> getListaAutoraRBr(ResultSet rs) throws SQLException{

		/*
		 * prolazimo kroz sve n-torke iz tabele
		 */
		while (rs.next()) {
			/*
			 * Kreiramo varijablu autor, u koju cemo upisati jednu n-torku
			 */
			MAutorRBr pom = new MAutorRBr();	
			
			/*
			 * rs.getInt("sifAutor") kupi iz trenutne n-torke vrijednost za kolonu "sifAutor" itd, i onda
			 * to upisuje u varijablu pom sa setterom.
			 */
			pom.setSifAutorRBr(rs.getInt("sifAutorRBr"));
			pom.setRBrAutora(rs.getInt("rBrAutora"));
			/*
			 * U kreirani vektor upisujemo dohvacenu n-torku koja je smjestena u "pom"
			 */
			listaAutoraRBr.add(pom);

		}
		
		/*
		 * Vracamo listu autora
		 */
		return listaAutoraRBr;

	}
	
}
