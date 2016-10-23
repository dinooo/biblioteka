package db.biblioteka.ba.fet;

import java.sql.SQLException;
import java.util.ArrayList;

import modeli.biblioteka.ba.fet.MAutor;
import modeli.biblioteka.ba.fet.MIzdavac;
import modeli.biblioteka.ba.fet.MKnjiga;
import modeli.biblioteka.ba.fet.MNastavnik;
import modeli.biblioteka.ba.fet.MObaveznost;
import modeli.biblioteka.ba.fet.MPredmet;
import modeli.biblioteka.ba.fet.MPrimjerak;
import modeli.biblioteka.ba.fet.MRezervacija;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakNastavnik;
import modeli.biblioteka.ba.fet.MRezervacijaPrimjerakStudent;
import modeli.biblioteka.ba.fet.MSemestar;
import modeli.biblioteka.ba.fet.MStudent;
import modeli.biblioteka.ba.fet.MVaznost;
import modeli.biblioteka.ba.fet.MVaznostObaveznost;
import modeli.biblioteka.ba.fet.MVrstaKnjige;
import tabele.biblioteka.ba.fet.TAutor;
import tabele.biblioteka.ba.fet.TIzdavac;
import tabele.biblioteka.ba.fet.TKnjiga;
import tabele.biblioteka.ba.fet.TNastavnik;
import tabele.biblioteka.ba.fet.TObaveznost;
import tabele.biblioteka.ba.fet.TPredmet;
import tabele.biblioteka.ba.fet.TPrimjerak;
import tabele.biblioteka.ba.fet.TRezervacija;
import tabele.biblioteka.ba.fet.TRezervacijaPrimjerakNastavnik;
import tabele.biblioteka.ba.fet.TRezervacijaPrimjerakStudent;
import tabele.biblioteka.ba.fet.TSemestar;
import tabele.biblioteka.ba.fet.TStudent;
import tabele.biblioteka.ba.fet.TVaznost;
import tabele.biblioteka.ba.fet.TVaznostObaveznost;
import tabele.biblioteka.ba.fet.TVrstaKnjige;

public class GetDbTables {
	
	public static ArrayList<MKnjiga> getTableKnjige(){
		TKnjiga.listaKnjiga.clear();
		try {
			DBKnjiga.getKnjiga();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return TKnjiga.listaKnjiga;
	}
	
	public static MKnjiga getKnjigaBySifra(int sifra){
		TKnjiga.listaKnjiga.clear();
		DBKnjiga.getKnjigaBySifra(sifra);
		return TKnjiga.listaKnjiga.get(0);
	}
	
	public static MKnjiga getKnjigaByOrigNaslov(String OriginalniNaslov){
		TKnjiga.listaKnjiga.clear();
		DBKnjiga.getKnjigaByOrigNaslov(OriginalniNaslov);
		return TKnjiga.listaKnjiga.get(0);
	}
	
	public static int getSifKnjigaByNaslov(String naslov){
		TKnjiga.listaKnjiga.clear();
		DBKnjiga.getSifKnjigaByNaslov(naslov);
		int sifra = -1;
		for (MKnjiga mKnjiga : TKnjiga.listaKnjiga) {
			sifra = mKnjiga.getSifKnjiga();
		}
		return sifra;
	}
	
	public static ArrayList<MKnjiga> getTableSlobodneKnjige(){
		TKnjiga.listaKnjiga.clear();
		try {
			DBKnjiga.getSlobodneKnjiga();;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return TKnjiga.listaKnjiga;
	}
	
	public static ArrayList<MKnjiga> getTableSlobodneKnjigeZaStudenta(int sifStudent){
		TKnjiga.listaKnjiga.clear();
		DBKnjiga.getSlobodneKnjigeBySifKoisnik(sifStudent, 2);
		return TKnjiga.listaKnjiga;
	}
	
	public static ArrayList<MKnjiga> getTableSlobodneKnjigeZaNastavnika(int sifNastavnik){
		TKnjiga.listaKnjiga.clear();
		DBKnjiga.getSlobodneKnjigeBySifKoisnik(sifNastavnik, 1);
		return TKnjiga.listaKnjiga;
	}
	
	public static ArrayList<MStudent> getTableStudenti(){
		TStudent.listaStudent.clear();
		DBStudent.getStudent();
		return TStudent.listaStudent;
	}
	
	public static ArrayList<MStudent> getStudentiSaZaduzenjima(){
		TStudent.listaStudent.clear();
		DBStudent.getStudentSaZaduzenjima();
		return TStudent.listaStudent;
	}
	
	public static MStudent getStudentBySifra(int sifra){
		TStudent.listaStudent.clear();
		DBStudent.getStudentBySifra(sifra);
		return TStudent.listaStudent.get(0);
	}
	
	public static int getSifStudentByPrezimeIme(String prezIme){
		TStudent.listaStudent.clear();
		DBStudent.getSifStudentByPrezimeIme(prezIme);
		return TStudent.listaStudent.get(0).getSifStudent();
	}
	
	public static ArrayList<MSemestar> getTableSemestr(){
		TSemestar.listaSemestar.clear();
		DBSemestar.getSemestar();
		return TSemestar.listaSemestar;
	}
	
	public static ArrayList<MRezervacijaPrimjerakStudent> getTableRezervacijaPrimjerakStudent(){
		TRezervacijaPrimjerakStudent.listaRezervacijaPrimjerakStudent.clear();
		DBRezervacijaPrimjerakStudent.getRezPrimjStud();
		return TRezervacijaPrimjerakStudent.listaRezervacijaPrimjerakStudent;
	}
	
	public static MRezervacijaPrimjerakStudent getRezPrimStudBySifra(int sifra){
		TRezervacijaPrimjerakStudent.listaRezervacijaPrimjerakStudent.clear();
		DBRezervacijaPrimjerakStudent.getRezPrimStudBySifRPS(sifra);
		return TRezervacijaPrimjerakStudent.listaRezervacijaPrimjerakStudent.get(0);
	}
	
	public static ArrayList<MPrimjerak> getTablePrimjerak(){
		TPrimjerak.listaPrimjerak.clear();
		DBPrimjerak.getPrimjerak();
		return TPrimjerak.listaPrimjerak;
	}
	
	public static MPrimjerak getPrimjerakByInventBroj(String inventarniBroj){
		TPrimjerak.listaPrimjerak.clear();
		DBPrimjerak.getPrimjerakByInvBr(inventarniBroj);
		return TPrimjerak.listaPrimjerak.get(0);
	}
	
	public static MPrimjerak getPrimjerakBySifra(int sifra){
		TPrimjerak.listaPrimjerak.clear();
		DBPrimjerak.getPrimjerakBySifra(sifra);
		return TPrimjerak.listaPrimjerak.get(0);
	}
	
	public static ArrayList<MRezervacija> getTableRezervacije(){
		TRezervacija.listaRezervacija.clear();
		DBRezervacija.getRezervacija();
		return TRezervacija.listaRezervacija;
	}
	
	public static MRezervacija getRezervacijaBySifra(int sifra){
		TRezervacija.listaRezervacija.clear();
		DBRezervacija.getRezervacijaBySifra(sifra);
		return TRezervacija.listaRezervacija.get(0);
	}
	
	public static MRezervacija getRezervacijaBySifPrimjerak(int sifra){
		TRezervacija.listaRezervacija.clear();
		DBRezervacija.getRezervacijaBySifraPrimjerak(sifra);
		return TRezervacija.listaRezervacija.get(0);
	}
	
	public static ArrayList<MRezervacija> getRezervacijaBySifStudent(int sifra){
		TRezervacija.listaRezervacija.clear();
		DBRezervacija.getRezervacijeBySifStudent(sifra);
		return TRezervacija.listaRezervacija;
	}
	
	public static ArrayList<MVrstaKnjige> getTableVrstaKnjige(){
		TVrstaKnjige.listaVrstaKnjige.clear();
		DBVrstaKnjige.getVrstaKnjige();
		return TVrstaKnjige.listaVrstaKnjige;
	}
	
	public static MVrstaKnjige getVrstaKnjigeBySifra(int sifra){
		TVrstaKnjige.listaVrstaKnjige.clear();
		DBVrstaKnjige.getVrstaKnjigeBySofra(sifra);
		return TVrstaKnjige.listaVrstaKnjige.get(0);
	}
	
	public static ArrayList<MIzdavac> getTableIzdavaci(){
		TIzdavac.listaIzdavaca.clear();
		DBIzdavac.getIzdavac();
		return TIzdavac.listaIzdavaca;
	}
	
	public static ArrayList<MAutor> getTableAutori(){
		TAutor.listaAutora.clear();; //prazimo vektor jer ga zelimo napuniti najnovijim podacima
		DBAutor.getAutor();
		return TAutor.listaAutora;
	}
	
	public static ArrayList<MNastavnik> getTableNastavnici(){
		TNastavnik.listaNastavnik.clear();
		DBNastavnik.getNastavnik();
		return TNastavnik.listaNastavnik;
	}
	
	public static ArrayList<MNastavnik> getNastavniciSaZaduzenjima(){
		TNastavnik.listaNastavnik.clear();
		DBNastavnik.getNastavnikSaZaduzenjima();
		return TNastavnik.listaNastavnik;
	}
	
	public static MNastavnik getNastavnikBySifra(int sifra){
		TNastavnik.listaNastavnik.clear();
		DBNastavnik.getNastavnikBySifra(sifra);
		return TNastavnik.listaNastavnik.get(0);
	}
	
	public static ArrayList<MPredmet> getTablePredmeti(){
		TPredmet.listaPredmet.clear();
		DBPredmet.getPredmet();
		return TPredmet.listaPredmet;	
	}
	
	public static int getSifPredmetByNaziv(String nazPredmet){
		TPredmet.listaPredmet.clear();
		DBPredmet.getSifPredmet(nazPredmet);
		int sifra =-1;
		for (MPredmet mPredmet : TPredmet.listaPredmet) {
			sifra = mPredmet.getSifPredmet();
		}
		return sifra;	
	}
	
	public static MPredmet getPredmetBynaziv(String nazPredmet){
		TPredmet.listaPredmet.clear();
		DBPredmet.getSifPredmet(nazPredmet);
		return 		TPredmet.listaPredmet.get(0);
	}
	
	public static ArrayList<MRezervacijaPrimjerakNastavnik> getTableRezervacijaPrimjerakNastavnik(){
		TRezervacijaPrimjerakNastavnik.listaRezervacijaPrimjerakNastavnik.clear();
		DBRezervacijaPrimjerakNastavnik.getRezPrimjNast();
		return TRezervacijaPrimjerakNastavnik.listaRezervacijaPrimjerakNastavnik;		
	}

	public static MRezervacijaPrimjerakNastavnik getRezPrimNastBySifra(int sifra){
		TRezervacijaPrimjerakNastavnik.listaRezervacijaPrimjerakNastavnik.clear();
		DBRezervacijaPrimjerakNastavnik.getRezPrimNastBySifRPS(sifra);
		return TRezervacijaPrimjerakNastavnik.listaRezervacijaPrimjerakNastavnik.get(0);
	}
	
	public static ArrayList<MVaznost> getTableVaznosti(){
		TVaznost.listaVaznost.clear();
		DBVaznost.getVaznost();
		return TVaznost.listaVaznost;
	}
	
	public static int getSifVaznost(int sifVaznost){
		TVaznost.listaVaznost.clear();
		DBVaznost.getSifVaznost(sifVaznost);
		int sifra = -1;
		for (MVaznost mVaznost : TVaznost.listaVaznost) {
			sifra = mVaznost.getSifVaznost();
		}
		return sifra;	
	}
	
	public static ArrayList<MObaveznost> getTableObaveznost(){
		TObaveznost.listaObaveznost.clear();
		DBObaveznost.getObaveznost();
		return TObaveznost.listaObaveznost;
	}
	
	public static int getSifObaveznost(int sifObaveznost){
		TObaveznost.listaObaveznost.clear();
		DBObaveznost.getSifObaveznost(sifObaveznost);
		int sifra = -1;
		for (MObaveznost mObav : TObaveznost.listaObaveznost) {
			sifra = mObav.getSifObaveznost();
		}
		return sifra;	
	}
	
	public static ArrayList<MVaznostObaveznost> getTableVaznostObaveznost(){
		TVaznostObaveznost.listaVaznostObaveznost.clear();
		DBVaznostObaveznost.getVaznObav();
		return TVaznostObaveznost.listaVaznostObaveznost;
	}
	
	public static int getSifVazObavBySifVaznostSifObaveznost(int sifVaznost, int sifObaveznost){
		TVaznostObaveznost.listaVaznostObaveznost.clear();
		DBVaznostObaveznost.getSifVazObav(sifVaznost, sifObaveznost);
		int sifra = -1;
		for (MVaznostObaveznost vazObav : TVaznostObaveznost.listaVaznostObaveznost) {
			sifra = vazObav.getSifVaznObav();
		}
		return sifra;
	}
}
