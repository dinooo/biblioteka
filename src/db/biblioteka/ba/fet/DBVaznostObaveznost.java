package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MVaznostObaveznost;
import tabele.biblioteka.ba.fet.TVaznostObaveznost;

public class DBVaznostObaveznost {

	public static void getVaznObav(){
		String SQL = "SELECT * FROM VaznostObaveznost";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TVaznostObaveznost.getListaVazObav(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}

	public static boolean insertKnjiPredObav(MVaznostObaveznost pom){
		String sqlInsert = "INSERT INTO VaznostObaveznost (sifVaznost, sifObaveznost) " + "VALUES (?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement(); //ovim linijama koda obilazimo foreign key consttraint
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			stmt.setInt(1, pom.getSifVaznost());
			stmt.setInt(2, pom.getSifObaveznost());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				pom.setSifVaznObav(newKey);
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
	
	public static void getSifVazObav(int sifVaznost, int sifObaveznost){
		String SQL2 = "SELECT * FROM VaznostObaveznost WHERE sifVaznost = ? "
				+ "  AND sifObaveznost = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifVaznost);
			preparedStatement.setInt(2, sifObaveznost);
			ResultSet rs = preparedStatement.executeQuery();
			TVaznostObaveznost.getListaVazObav(rs);
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
