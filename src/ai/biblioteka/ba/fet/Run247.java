package ai.biblioteka.ba.fet;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import db.biblioteka.ba.fet.DBKnjiga;
import db.biblioteka.ba.fet.DBNastavnik;
import db.biblioteka.ba.fet.DBPrimjerak;
import db.biblioteka.ba.fet.DBRezervacija;
import db.biblioteka.ba.fet.DBRezervacijaPrimjerakNastavnik;
import db.biblioteka.ba.fet.DBRezervacijaPrimjerakStudent;
import db.biblioteka.ba.fet.DBStudent;
import db.biblioteka.ba.fet.GetDbTables;
import gui.biblioteka.ba.fet.LoginGUI;
import modeli.biblioteka.ba.fet.MKnjiga;
import modeli.biblioteka.ba.fet.MNastavnik;
import modeli.biblioteka.ba.fet.MPrimjerak;
import modeli.biblioteka.ba.fet.MRezervacija;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakNastavnik;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakStudent;
import modeli.biblioteka.ba.fet.MStudent;

public class Run247 {

	public static void main(String[] args) {		
		LoginGUI.startLogin();

		java.util.Date date= new java.util.Date();
		Timer timer = new Timer();

		timer.schedule(new TimerTask(){
			public void run(){
				provjeriStudentRezervacija();
				provjeriNastavnikRezervacija();
				provjeriStudentVracanje();
				provjeriNastavnikVracanje();
				resetNegativniBodoviPocetakSkolske();
			}
		},date, 24*60*60*1000);//24*60*60*1000 add 24 hours delay between job executions.
	}

	public static void provjeriStudentRezervacija(){

		//provjera se vrsi za svakog studenta
		for (MStudent stud : GetDbTables.getTableStudenti()) {
			//Provjera samo onih studenata koji su nesto rezervisali
			if(stud.getBrRezervacija() != 0){

				//prolazimo kroz sve rezervacije koje je student izvrsio
				for (MRezervacija rezervacija : GetDbTables.getTableRezervacije()) {
					//zanimaju nas samo rezervacije koje nisu odobrene (zaduzene) za studente, pa samo njih uzimamo u obzir
					if(rezervacija.getSifKorisnik() == stud.getSifStudent() && rezervacija.getNastStud() == 2 && rezervacija.getOdobrena() == 0){
						/*
						 * treba provjeriti da li je proslo 5 dana od zaduzivanja rezervacije. 
						 * ako jeste rezervaciju osloboditi, tj osloboditi primjerak, a studentu dati negativne bodove
						 */

						Date dateRok = new Date(rezervacija.getDatRezervacija().getTime() + 5*24*3600*1000l);						
						java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
						java.util.Date utilDate = cal.getTime();
						Date todayDate = new Date(utilDate.getTime());// Danasnji datum;

						/*
						 * imamo trenutni datum, i datum u prekoracenju (rezervacija + 5 dana).
						 * ako danasnji datum predje datum prekoracenja, znaci da student nije dosao po knjigu
						 * Ako je student prekoracio rok podizanja, onda mu se pisu negativni bodovi za svaku rezervisanu knjigu koja mu nije izdata.
						 * to znaci da dodatno treba u rezervaciji provjeriti da li je odobrena ili nije. Negativne bodove dobiva samo ako rezerv
						 * nije odobrena
						 */
						if(todayDate.after(dateRok)){

							/*
							 * trebamo sada da dohvatimo koja je knjiga u pitanju, pa da od nje dobijemo iznos negativnih bodova.
							 * dohvatamo prvo tabeul rezervacijaPrimjerakStudent. iz koje na osnovu sifRezzervacije pronadjemo sifru primjerka, 
							 * pa na osnov sifre primjerka i knjigu pa konacnoi negativne bodove.
							 */
							int sifPrimjerka = -1;
							MRezervacijaPrimjerakStudent mRps = new MRezervacijaPrimjerakStudent();
							for (MRezervacijaPrimjerakStudent rps : GetDbTables.getTableRezervacijaPrimjerakStudent()) {
								if(rps.getSifRezervacija() == rezervacija.getSifRezervacija()){
									sifPrimjerka = rps.getSifPrimjerak();
									mRps = rps;
									break;
								}
							}
							//tabela primjerka
							int sifKnjige = -1; //upisujemo sifru dohvacene knjige
							MPrimjerak mPrimjerak = new MPrimjerak();
							for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
								if(primj.getSifPrimjerak() == sifPrimjerka){
									sifKnjige = primj.getSifKnjiga();
									mPrimjerak = primj;
									break;
								}
							}
							//na osnovu sifknjige iz tabele knjiga pronadjemo koja je knjiga u pitanju
							int brNegBodova = 0; //sadrzavat ce broj negativih bodova koje knjiga nosi
							MKnjiga mKnjiga = new MKnjiga(); //knjiga koja je u pitanju
							for (MKnjiga knj : GetDbTables.getTableKnjige()) {
								if(knj.getSifKnjiga() == sifKnjige){
									brNegBodova = knj.getNegBodovi();
									mKnjiga = knj;
									break;
								}
							}

							/*
							 * sada treba ubaciti negativne bodove studentu za datu knjigu
							 */
							int negBodovi = stud.getNegBodovi(); //uzimamo trenutni broj negativnih bodova koje student ima.
							negBodovi = negBodovi + brNegBodova * 5; //negativne bodove za 5 dana kasnjenja
							/*
							 * upisujemo br neg bodova u BP 
							 */
							DBStudent.updateNegBodovi(negBodovi, stud.getSifStudent());

							/*
							 * oslobadjanje knjige koju je student bio rezervisao
							 * To znaci da moramo u tabeli knjiga povecat brPrimjeraka.
							 * Takodje u tabeli Primjerak moramo taj primjerak staviti da nije rezervisan
							 * Za svaki slucaj obrisati n-torku iz RezervacijaPrimjerakStudent, kao i iz tabele Rezervacija
							 * da ne bi dolazilo do kolizije kad opet neko taj primjerak zeli da zaduzi
							 */
							DBKnjiga.updateBrPrimjeraka(mKnjiga.getSifKnjiga(), mKnjiga.getBrPrimjeraka() + 1);
							DBPrimjerak.updateRezervisan(0, mPrimjerak.getSifPrimjerak());
							DBRezervacijaPrimjerakStudent.deleteRezPrimjStud(mRps.getSifRezPrimStud());
							DBRezervacija.deleteRezervacija(rezervacija.getSifRezervacija());
						}
					}
				}	
			}	
		}
	}

	public static void provjeriNastavnikRezervacija(){

		for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
			if(nast.getBrRezervacija() != 0){
				for (MRezervacija rezervacija : GetDbTables.getTableRezervacije()) {
					if(rezervacija.getSifKorisnik() == nast.getSifNastavnik() && rezervacija.getNastStud() == 1 && rezervacija.getOdobrena() == 0){
						Date dateRok = new Date(rezervacija.getDatRezervacija().getTime() + 5*24*3600*1000l);
						java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
						java.util.Date utilDate = cal.getTime();
						Date todayDate = new Date(utilDate.getTime());// Danasnji datum;
						if(todayDate.after(dateRok)){
							int sifPrimjerka = -1;
							MRezervacijaPrimjerakNastavnik mRpn = new MRezervacijaPrimjerakNastavnik();
							for (MRezervacijaPrimjerakNastavnik rpn : GetDbTables.getTableRezervacijaPrimjerakNastavnik()) {
								if(rpn.getSifRezervacija() == rezervacija.getSifRezervacija()){
									sifPrimjerka = rpn.getSifPrimjerak();
									mRpn = rpn;
									break;
								}
							}
							int sifKnjige = -1;
							MPrimjerak mPrimjerak = new MPrimjerak();
							for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
								if(primj.getSifPrimjerak() == sifPrimjerka){
									sifKnjige = primj.getSifKnjiga();
									mPrimjerak = primj;
									break;
								}
							}
							int brNegBodova = 0; 
							MKnjiga mKnjiga = new MKnjiga();
							for (MKnjiga knj : GetDbTables.getTableKnjige()) {
								if(knj.getSifKnjiga() == sifKnjige){
									brNegBodova = knj.getNegBodovi();
									mKnjiga = knj;
									break;
								}
							}
							int negBodovi = nast.getNegBodovi();
							negBodovi = negBodovi + brNegBodova * 5;

							DBNastavnik.updateNegBodovi(negBodovi, nast.getSifNastavnik());

							DBKnjiga.updateBrPrimjeraka(mKnjiga.getSifKnjiga(), mKnjiga.getBrPrimjeraka() + 1);
							DBPrimjerak.updateRezervisan(0, mPrimjerak.getSifPrimjerak());
							DBRezervacijaPrimjerakNastavnik.deleteRezPrimjNast(mRpn.getSifRezPrimNast());
							DBRezervacija.deleteRezervacija(rezervacija.getSifRezervacija());
						}
					}
				}	
			}	
		}
	}

	public static void provjeriStudentVracanje(){
		//prolazimo provjeru za svakog studenta
		for (MStudent stud : GetDbTables.getTableStudenti()) {
			//prolazimo kroz svaku rezervaciju, i hocemo za odredjenog studenta da pokupimo rezervaciju koju je ucinio
			MRezervacija rezervacija = new MRezervacija();
			//zanimaju nas samo studenti koji imaju iznajmljene knjige
			if(stud.getBrPosudjenihKnjiga() != 0){
				for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
					//rezOdobrena=0 kada se radi o rezervaciji. kada je 1, tada je vec posudjena knjiga
					if(rez.getNastStud() == 2 && rez.getSifKorisnik()==stud.getSifStudent()  && rez.getOdobrena() == 1){//naststud = 2 za studente, a 1 za nastavnike
						Date datVracanja = rez.getDatVracanja();	
						java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
						java.util.Date utilDate = cal.getTime();
						Date todayDate = new Date(utilDate.getTime());//Danasnji datum

						/*
						 * imamo trenutni datum, i datum vracanja. ako datum vracanja bude veci od danasnjeg,onda mu se pisu negativni bodovi
						 */
						if(datVracanja.after(todayDate)){

							int sifPrimjerka = -1;
							for (MRezervacijaPrimjerakStudent rps : GetDbTables.getTableRezervacijaPrimjerakStudent()) {
								if(rps.getSifRezervacija() == rezervacija.getSifRezervacija()){
									sifPrimjerka = rps.getSifPrimjerak();
									break;
								}
							}
							//tabela primjerka
							int sifKnjige = -1; //upisujemo sifru dohvacene knjige
							for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
								if(primj.getSifPrimjerak() == sifPrimjerka){
									sifKnjige = primj.getSifKnjiga();
									break;
								}
							}
							//na osnovu sifknjige iz tabele knjiga pronadjemo koja je knjiga u pitanju
							int brNegBodova = 0; //sadrzavat ce broj negativih bodova koje knjiga nosi
							for (MKnjiga knj : GetDbTables.getTableKnjige()) {
								if(knj.getSifKnjiga() == sifKnjige){
									brNegBodova = knj.getNegBodovi();
									break;
								}
							}

							/*
							 * sada treba ubaciti negativne bodove studentu za datu knjigu
							 */
							int negBodovi = stud.getNegBodovi(); //uzimamo trenutni broj negativnih bodova koje student ima.
							negBodovi = negBodovi + brNegBodova;

							/*
							 * upisujemo br neg bodova u BP 
							 */
							DBStudent.updateNegBodovi(negBodovi, stud.getSifStudent());		
						}
					}	
				}
			}
		}
	}

	public static void provjeriNastavnikVracanje(){
		for (MNastavnik nast : GetDbTables.getTableNastavnici()) {
			MRezervacija rezervacija = new MRezervacija();
			if(nast.getBrPosudjenihKnjiga() != 0){
				for (MRezervacija rez : GetDbTables.getTableRezervacije()) {
					if(rez.getNastStud() == 1 && rez.getSifKorisnik()==nast.getSifNastavnik()  && rez.getOdobrena() == 1){//naststud = 2 za studente, a 1 za nastavnike
						Date datVracanja = rez.getDatVracanja();	
						java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
						java.util.Date utilDate = cal.getTime();
						Date todayDate = new Date(utilDate.getTime());//Danasnji datum
						if(datVracanja.after(todayDate)){
							int sifPrimjerka = -1;
							for (MRezervacijaPrimjerakNastavnik rpn : GetDbTables.getTableRezervacijaPrimjerakNastavnik()) {
								if(rpn.getSifRezervacija() == rezervacija.getSifRezervacija()){
									sifPrimjerka = rpn.getSifPrimjerak();
									break;
								}
							}
							int sifKnjige = -1;
							for (MPrimjerak primj : GetDbTables.getTablePrimjerak()) {
								if(primj.getSifPrimjerak() == sifPrimjerka){
									sifKnjige = primj.getSifKnjiga();
									break;
								}
							}
							int brNegBodova = 0; 
							for (MKnjiga knj : GetDbTables.getTableKnjige()) {
								if(knj.getSifKnjiga() == sifKnjige){
									brNegBodova = knj.getNegBodovi();
									break;
								}
							}

							int negBodovi = nast.getNegBodovi(); //uzimamo trenutni broj negativnih bodova koje student ima.
							negBodovi = negBodovi + brNegBodova;
							DBNastavnik.updateNegBodovi(negBodovi, nast.getSifNastavnik());}
					}	
				}
			}
		}
	}	

	public static void resetNegativniBodoviPocetakSkolske(){
		/*
		 * Na pocetku skolske godine se negativni bodovi resetuju ukoliko korisnici imaju 0 zaduzenih knjiga
		 */
		java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
		
		Date pocetakGodine = Date.valueOf("0000-9-1");
		
		java.util.GregorianCalendar calPocetakGodine = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
		cal.setTime(pocetakGodine);
		
		int pocetakGodineDan = calPocetakGodine.get(GregorianCalendar.DAY_OF_MONTH);
		int pocetakGodineMjesec = calPocetakGodine.get(GregorianCalendar.MONTH);

		int danasDan = cal.get(GregorianCalendar.DAY_OF_MONTH);
		int danasMjesec = cal.get(GregorianCalendar.MONTH);
				
		/*
		 * ukoliko se dan i mjesec od skolske godine poklapaju, izvrsava resetovanje bodova. Godina se ne mora poklapati
		 * jer svake godine je druga godina u pitanju
		 */
		if(pocetakGodineDan == danasDan && pocetakGodineMjesec == danasMjesec){
			for (MStudent mStudent : GetDbTables.getTableStudenti()) {
				if(mStudent.getBrPosudjenihKnjiga() == 0){
					DBStudent.updateNegBodovi(0, mStudent.getSifStudent());
				}
			}
			
			for (MNastavnik mNastavnik : GetDbTables.getTableNastavnici()) {
				if(mNastavnik.getBrPosudjenihKnjiga() == 0){
					DBNastavnik.updateNegBodovi(0, mNastavnik.getSifNastavnik());
				}
			}			
		}
		
	}
}
