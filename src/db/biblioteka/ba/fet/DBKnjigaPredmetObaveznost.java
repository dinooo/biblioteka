package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MKnjigaPredmetObaveznost;
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
}
