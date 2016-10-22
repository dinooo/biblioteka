package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MKnjigaAutorRBr;
import tabele.biblioteka.ba.fet.TKnjigaAutorRBr;

public class DBKnjigaAutorRBr {

	public static void getKnjigaAutorRBr(){
		String SQL = "SELECT * FROM KnjigaAutorRBr";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TKnjigaAutorRBr.getListaKnjigaAutor(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}

	public static boolean insertKnjigaAutorRBr(MKnjigaAutorRBr pom){
		String sqlInsert = "INSERT INTO KnjigaAutorRBr (sifKnjiga, sifAutor, sifAutorRBr) " + "VALUES (?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement(); //ovim linijama koda obilazimo foreign key consttraint
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			
			stmt.setInt(1, pom.getSifKnjiga());
			stmt.setInt(2, pom.getSifAutor());
			stmt.setInt(3, pom.getSifAutorRBr());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				pom.setSifKnjigaAutorRBr(newKey);
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
}