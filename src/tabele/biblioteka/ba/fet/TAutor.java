package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MAutor;

/*
 * Preko klasa TAutor i ostalih koje pocinju sa "T..." upisujemo i citamo n-torke u/iz BP
 */
public class TAutor {

	public static ArrayList<MAutor> listaAutora = new ArrayList<>();
	
	/*
	 * Sljedeci metod iz tabele Autor iz BP kupi sve autore i snima u iznad kreiranu listu autora
	 */
	public static ArrayList<MAutor> getListaAutora(ResultSet rs) throws SQLException{

		/*
		 * prolazimo kroz sve n-torke iz tabele
		 */
		while (rs.next()) {
			/*
			 * Kreiramo varijablu autor, u koju cemo upisati jednu n-torku
			 */
			MAutor pom = new MAutor();	
			
			/*
			 * rs.getInt("sifAutor") kupi iz trenutne n-torke vrijednost za kolonu "sifAutor" itd, i onda
			 * to upisuje u varijablu pom sa setterom.
			 */
			pom.setSifAutor(rs.getInt("sifAutor"));
			pom.setImeAutor(rs.getString("imeAutor"));
			pom.setPrezAutor(rs.getString("prezAutor"));

			/*
			 * U kreirani vektor upisujemo dohvacenu n-torku koja je smjestena u "pom"
			 */
			listaAutora.add(pom);

		}
		
		/*
		 * Vracamo listu autora
		 */
		return listaAutora;

	}

}
