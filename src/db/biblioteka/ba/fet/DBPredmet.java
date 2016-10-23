package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MPredmet;
import tabele.biblioteka.ba.fet.TKnjiga;
import tabele.biblioteka.ba.fet.TPredmet;

public class DBPredmet {

	
	public static void getPredmet(){
		String SQL = "SELECT * FROM predmet";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TPredmet.getListaPredmet(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	public static void getPredmetiOdNastavnika(String prezIme){
		
		String[] niz = prezIme.split(" ");
		String SQL2 = "SELECT * FROM predmet WHERE sifNastavnik IN (SELECT sifNastavnik FROM nastavnik WHERE prezNastavnik = ? AND imeNastavnik = ?)";	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, niz[0]);
			preparedStatement.setString(2, niz[1]);
			
			ResultSet rs = preparedStatement.executeQuery();
			TPredmet.getListaPredmet(rs);
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
	
	
	public static boolean insertPredmet(MPredmet predmet){
		String sqlInsert = "INSERT INTO predmet (nazPredmet, kratPredmet, sifSemestar, sifNastavnik) " + 
		"VALUES (?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, predmet.getNazPredmet());
			stmt.setString(2, predmet.getKratPredmet());
			stmt.setInt(3, predmet.getSifSemestar());
			stmt.setInt(4, predmet.getSifnastavnik());
			int affected = stmt.executeUpdate();

			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				predmet.setSifPredmet(newKey);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}
	
	public static void getSifPredmet(String nazivPredmet){
		
		String SQL2 = "SELECT * FROM predmet WHERE nazPredmet = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, nazivPredmet);
			ResultSet rs = preparedStatement.executeQuery();
			TPredmet.getListaPredmet(rs);
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
	
}
