package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MPrimjerak;
import tabele.biblioteka.ba.fet.TPrimjerak;

public class DBPrimjerak {


	public static void getPrimjerak(){
		String SQL = "SELECT * FROM primjerak";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TPrimjerak.getListaPrimjerak(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}

	public static boolean insertPredmet(MPrimjerak primjerak){
		String sqlInsert = "INSERT INTO primjerak (inventarniBr, datumNabavke, stanje, rezervisan, sifKnjiga) " + 
				"VALUES (?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, primjerak.getInventartniBr());
			stmt.setDate(2, primjerak.getDatumNabavke());
			stmt.setString(3, primjerak.getStanje());
			stmt.setInt(4, primjerak.getRezervisan());
			stmt.setInt(5, primjerak.getSifKnjiga());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				primjerak.setSifPrimjerak(newKey);
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
	
	public static boolean updateRezervisan(int rezervisan, int sifPrimjerak){
		String sqlUpdate = "UPDATE primjerak SET rezervisan = ? WHERE sifPrimjerak = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, rezervisan);
			stmt.setInt(2, sifPrimjerak);
			stmt.executeUpdate();	
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 	
		return true;	
	}
	
	public static boolean updateStanje(String stanje, int sifPrimjerak){
		String sqlUpdate = "UPDATE primjerak SET stanje = ? WHERE sifPrimjerak = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setString(1, stanje);
			stmt.setInt(2, sifPrimjerak);
			stmt.executeUpdate();	
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 	
		return true;	
	}
	
	public static void getPrimjerakBySifra(int sifra){
		String SQL2 = "SELECT * FROM primjerak WHERE sifPrimjerak = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifra);
			ResultSet rs = preparedStatement.executeQuery();
			TPrimjerak.getListaPrimjerak(rs);
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
	
	public static void getPrimjerakByInvBr(String invBr){
		String SQL2 = "SELECT * FROM primjerak WHERE inventarniBr = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, invBr);
			ResultSet rs = preparedStatement.executeQuery();
			TPrimjerak.getListaPrimjerak(rs);
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
