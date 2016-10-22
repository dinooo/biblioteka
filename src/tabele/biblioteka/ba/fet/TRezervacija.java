package tabele.biblioteka.ba.fet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MRezervacija;

public class TRezervacija {

	public static ArrayList<MRezervacija> listaRezervacija = new ArrayList<>();

	public static ArrayList<MRezervacija> getListaRezervacija(ResultSet rs) throws SQLException{

		while (rs.next()) {

			MRezervacija pom = new MRezervacija();	
			
			pom.setSifRezervacija(rs.getInt("sifRezervacija"));
			pom.setDatRezervacija(rs.getDate("datRezervacija"));
			pom.setDatPodizanja(rs.getDate("datPodizanja"));
			pom.setDatVracanja(rs.getDate("datVracanja"));
			pom.setDatKadVracena(rs.getDate("datKadVracena"));
			pom.setSifKorisnik(rs.getInt("sifKorisnik"));
			pom.setSifPrimjerak(rs.getInt("sifPrimjerak"));
			pom.setNastStud(rs.getInt("nastStud"));
			pom.setOdobrena(rs.getInt("odobrena"));
			listaRezervacija.add(pom);

		}

		return listaRezervacija;
	}
	
	
}
