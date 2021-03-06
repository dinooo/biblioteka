package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MKnjigaPredmetObaveznost;
import tabele.biblioteka.ba.fet.TKnjiga;
import tabele.biblioteka.ba.fet.TKnjigaPredmetObaveznost;

public class DBKnjigaPredmetObaveznost {

	public static void getKnjiPredObav(){
		String SQL = "SELECT * FROM KnjigaPredmetObaveznost";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TKnjigaPredmetObaveznost.getListaKnjigaPredmetObaveznost(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static void getKnjiPredObavZaPredmet(String predmet){
		String SQL2 = "SELECT * FROM KnjigaPredmetObaveznost WHERE sifPredmet IN (SELECT sifPredmet FROM predmet WHERE nazPredmet = ?)";	
					
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, predmet);
			
			ResultSet rs = preparedStatement.executeQuery();
			TKnjigaPredmetObaveznost.getListaKnjigaPredmetObaveznost(rs);
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
	
	public static boolean insertKnjiPredObav(MKnjigaPredmetObaveznost pom){
		String sqlInsert = "INSERT INTO KnjigaPredmetObaveznost (sifVaznObav, sifKnjiga, sifPredmet) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {			
			Statement stmtPom1 = conn.createStatement(); //ovim linijama koda obilazimo foreign key consttraint
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			stmt.setInt(1, pom.getSifVaznObav());
			stmt.setInt(2, pom.getSifKnjiga());
			stmt.setInt(3, pom.getSifPredmet());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				pom.setSifKnjPredObav(newKey);
			} else {
				System.err.println("Nijedan red nije izmjenjen");
				return false;
			}
			Statement stmtPom2 = conn.createStatement(); //foreign key constraint
			stmtPom2.execute("SET FOREIGN_KEY_CHECKS=1");
			stmtPom2.close();			
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

	public static boolean deleteLiteraturu(int sifPredmet){
		String sqlInsert = "DELETE FROM KnjigaPredmetObaveznost WHERE sifPredmet = ?";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, sifPredmet);
			stmt.executeUpdate();
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

	public static void getSifKnjPredObavZaPredmet(int sifPredmet, int vaznost){
		String SQL2 = "SELECT * FROM KnjigaPredmetObaveznost WHERE sifPredmet = ? AND sifVaznObav IN (SELECT sifVaznObav FROM VaznostObaveznost WHERE sifVaznost = ?  AND sifObaveznost = 1)";	
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifPredmet);
			preparedStatement.setInt(2, vaznost);

			
			ResultSet rs = preparedStatement.executeQuery();
			TKnjigaPredmetObaveznost.getListaKnjigaPredmetObaveznost(rs);
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

	public static void getSifKnjPredNeObavZaPredmet(int sifPredmet, int vaznost){
		String SQL2 = "SELECT * FROM KnjigaPredmetObaveznost WHERE sifPredmet = ? AND sifVaznObav IN (SELECT sifVaznObav FROM VaznostObaveznost WHERE sifVaznost = ? AND sifObaveznost = 2)";	
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifPredmet);
			preparedStatement.setInt(2, vaznost);

			
			ResultSet rs = preparedStatement.executeQuery();
			TKnjigaPredmetObaveznost.getListaKnjigaPredmetObaveznost(rs);
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
	

	public static boolean updateKnjigaPredmetObaveznost(int sifKnjPredObav, int sifVaznObav, int sifKnjiga, int sifPredmet){
		String sqlUpdate = "UPDATE KnjigaPredmetObaveznost SET sifVaznObav = ?, sifKnjiga = ?, sifPredmet = ? WHERE sifKnjPredObav = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, sifVaznObav);
			stmt.setInt(2, sifKnjiga);
			stmt.setInt(3, sifPredmet);
			stmt.setInt(4, sifKnjPredObav);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
}
