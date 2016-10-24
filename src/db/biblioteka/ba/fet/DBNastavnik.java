package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MNastavnik;
import tabele.biblioteka.ba.fet.TNastavnik;

public class DBNastavnik {

	public static void getNastavnik(){
		String SQL = "SELECT * FROM nastavnik";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TNastavnik.getListaNastavnik(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static boolean insertNastavnik(MNastavnik nastavnik){

		String sqlInsert = "INSERT INTO nastavnik (imeNastavnik, prezNastavnik, zvanje, negBodovi, password, bibliotekar, brPosudjenihKnjiga, brRezervacija) " + 
		"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, nastavnik.getImeNastavnik());
			stmt.setString(2, nastavnik.getPrezNastavnik());
			stmt.setString(3, nastavnik.getZvanje());
			stmt.setInt(4, nastavnik.getNegBodovi());
			stmt.setString(5, nastavnik.getPassword());
			stmt.setInt(6, nastavnik.getBibliotekar());
			stmt.setInt(7, nastavnik.getBrPosudjenihKnjiga());
			stmt.setInt(8, nastavnik.getBrRezervacija());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				nastavnik.setSifNastavnik(newKey);
			} else {
				System.err.println("Nijedan red nije izmjenjen");
				return false;
			}
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		finally {
			if(keys != null)
				try {
					keys.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return true;
	}	
	
	public static boolean updateNastavnik(String ime, String prezime, String password, int sifNastavnik){
		String sqlUpdate = "UPDATE nastavnik SET imeNastavnik = ?, prezNastavnik =?, password = ? WHERE sifNastavnik = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, ime);
			stmt.setString(2, prezime);
			stmt.setString(3, password);
			stmt.setInt(4, sifNastavnik);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updatePassword(String password, int sifNastavnik){
		String sqlUpdate = "UPDATE nastavnik SET password = ? WHERE sifNastavnik = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, password);
			stmt.setInt(2, sifNastavnik);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updateNegBodovi(int bodovi, int sifNastavnik){
		String sqlUpdate = "UPDATE nastavnik SET negBodovi = ? WHERE sifNastavnik = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, bodovi);
			stmt.setInt(2, sifNastavnik);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updateZvanje(String zvanje, int sifNastavnik){
		String sqlUpdate = "UPDATE nastavnik SET zvanje = ? WHERE sifNastavnik = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, zvanje);
			stmt.setInt(2, sifNastavnik);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updateBrRezervacija(int brRezervacija, int sifNastavnik){
		String sqlUpdate = "UPDATE nastavnik SET brRezervacija = ? WHERE sifNastavnik = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, brRezervacija);
			stmt.setInt(2, sifNastavnik);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updateBrPosudjenihKnjiga(int brPosudjenih, int sifNastavnik){
		String sqlUpdate = "UPDATE nastavnik SET brPosudjenihKnjiga = ? WHERE sifNastavnik = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, brPosudjenih);
			stmt.setInt(2, sifNastavnik);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static void getNastavnikBySifra(int sifra){
		String SQL2 = "SELECT * FROM nastavnik WHERE sifNastavnik = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifra);
			ResultSet rs = preparedStatement.executeQuery();
			TNastavnik.getListaNastavnik(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void getNastavnikSaZaduzenjima(){
		String SQL = "SELECT * FROM nastavnik WHERE brPosudjenihKnjiga > 0";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TNastavnik.getListaNastavnik(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
}
