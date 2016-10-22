package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MVaznost;
import tabele.biblioteka.ba.fet.TVaznost;

public class DBVaznost {

	public static void getVaznost(){
		String SQL = "SELECT * FROM vaznost";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TVaznost.getListaVaznost(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}

	public static boolean insertAutor(MVaznost vaznost){
		String sqlInsert = "INSERT INTO vaznost (rBrVaznost) " + "VALUES (?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, vaznost.getrBrVaznost());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				vaznost.setSifVaznost(newKey);
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
	
	public static void getSifVaznost(int sifVaznost){
		String SQL2 = "SELECT * FROM vaznost WHERE sifVaznost = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifVaznost);
			ResultSet rs = preparedStatement.executeQuery();
			TVaznost.getListaVaznost(rs);
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
