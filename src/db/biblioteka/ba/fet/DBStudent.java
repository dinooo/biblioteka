package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MStudent;
import tabele.biblioteka.ba.fet.TStudent;

public class DBStudent {
	
	public static void getStudent(){
	String SQL = "SELECT * FROM student";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TStudent.getListaStudent(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static boolean insertStudent(MStudent student){
		String sqlInsert = "INSERT INTO student (imeStudent, prezStudent, brIndexa, negBodovi, password, brPosudjenihKnjiga, brRezervacija, sifSemestar) " + 
		"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, student.getImeStudent());
			stmt.setString(2, student.getPrezStudent());
			stmt.setString(3, student.getBrIndexa());
			stmt.setInt(4, student.getNegBodovi());
			stmt.setString(5, student.getPassword());
			stmt.setInt(6, student.getBrPosudjenihKnjiga());
			stmt.setInt(7, student.getBrRezervacija());
			stmt.setInt(8, student.getSifSemestar());			
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				student.setSifStudent(newKey);
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
	
	public static boolean updatePassword(String password, int sifStudent){
		String sqlUpdate = "UPDATE student SET password = ? WHERE sifStudent = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, password);
			stmt.setInt(2, sifStudent);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updateNegBodovi(int bodovi, int sifStudent){
		String sqlUpdate = "UPDATE student SET negBodovi = ? WHERE sifStudent = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, bodovi);
			stmt.setInt(2, sifStudent);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 		
		return true;
	}
	
	public static boolean updateBrPosudjenihKnjiga(int brPosudjenih, int sifStudent){
		String sqlUpdate = "UPDATE student SET brPosudjenihKnjiga = ? WHERE sifStudent = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, brPosudjenih);
			stmt.setInt(2, sifStudent);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static boolean updateBrRezervacija(int brRezervacija, int sifStudent){
		String sqlUpdate = "UPDATE student SET brRezervacija = ? WHERE sifStudent = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, brRezervacija);
			stmt.setInt(2, sifStudent);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static void getStudentBySifra(int sifra){
		String SQL2 = "SELECT * FROM student WHERE sifStudent = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifra);
			ResultSet rs = preparedStatement.executeQuery();
			TStudent.getListaStudent(rs);
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
	
	public static void getSifStudentByPrezimeIme(String prezimeIme){
		String[] niz = prezimeIme.split(" ");
		String SQL2 = "SELECT * FROM student WHERE prezStudent = ? AND imeStudent = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, niz[1]);
			preparedStatement.setString(2, niz[0]);

			ResultSet rs = preparedStatement.executeQuery();
			TStudent.getListaStudent(rs);
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
	
	
	
	
	public static void getStudentSaZaduzenjima(){
		String SQL = "SELECT * FROM student WHERE brPosudjenihKnjiga > 0";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TStudent.getListaStudent(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
}
