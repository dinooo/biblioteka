package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import modeli.biblioteka.ba.fet.MAutor;
import tabele.biblioteka.ba.fet.TAutor;

public class DBAutor {


	/*
	 * citanje iz BP
	 */
	public static void getAutor(){
		String SQL = "SELECT * FROM autor";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TAutor.getListaAutora(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}

	/*
	 * pisanje u BP
	 */
	public static boolean insertAutor(MAutor autor){
		String sqlInsert = "INSERT INTO autor (imeAutor, prezAutor) " + "VALUES (?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, autor.getImeAutor());
			stmt.setString(2, autor.getPrezAutor());
			int affected = stmt.executeUpdate();
			/*
			 * Za automatsko inkrementiranje primarnog kljuca
			 */
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				autor.setSifAutor(newKey);
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
}
