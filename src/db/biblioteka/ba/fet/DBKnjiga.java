package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeli.biblioteka.ba.fet.MKnjiga;
import tabele.biblioteka.ba.fet.TKnjiga;

public class DBKnjiga {

	public static void getKnjiga() throws SQLException {
		String SQL = "SELECT * FROM knjiga";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			TKnjiga.getListaKnjiga(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static void getZaduzeneKnjigeZaKorisnika(int sifKorisnik, int nastStud){	
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN "
				+ " (SELECT sifKnjiga FROM primjerak WHERE sifPrimjerak IN "
				+ " (SELECT sifPrimjerak FROM rezervacija WHERE sifKorisnik = ? AND nastStud = ?)))";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifKorisnik);
			preparedStatement.setInt(2, nastStud);
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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
	
	public static void getSlobodneKnjiga() throws SQLException {
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN (SELECT sifKnjiga FROM primjerak WHERE rezervisan = 0)";	
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL2);
				) {
			TKnjiga.getListaKnjiga(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static void getSlobodneKnjigeBySifKoisnik(int sifKorisnik, int tipKorisnik){
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN (SELECT sifKnjiga FROM primjerak WHERE rezervisan = 0 AND "
				+ "sifPrimjerak IN (SELECT sifPrimjerak FROM rezervacija WHERE sifKorisnik = ? AND nastStud = ? "
				+ "GROUP BY sifKnjiga))";	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifKorisnik);
			preparedStatement.setInt(2, tipKorisnik);
			
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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
	
	public static void getSifKnjigaByNaslov(String naslov){
		String SQL2 = "SELECT * FROM knjiga WHERE naslov = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, naslov);
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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

	public static boolean insertKnjiga(MKnjiga knjiga) throws SQLException {	
		String sqlInsert = "INSERT INTO knjiga (naslov, origNaslov, brStranica, godIzdanja, negBodovi, "
				+ "brPrimjeraka, sifIzdavac, sifVrstaKnjige) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement();
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			stmt.setString(1, knjiga.getNaslov());
			stmt.setString(2, knjiga.getOrigNaslov());
			stmt.setInt(3, knjiga.getBrStranica());
			stmt.setDate(4, knjiga.getGodIzdanja());
			stmt.setInt(5, knjiga.getNegBodovi());
			stmt.setInt(6, knjiga.getBrPrimjeraka());
			stmt.setInt(7, knjiga.getSifIzdavac());
			stmt.setInt(8, knjiga.getSifVrstaKnjige());
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				knjiga.setSifKnjiga(newKey);
			} else {
				System.err.println("Nijedan red nije izmjenjen");
				return false;
			}
			Statement stmtPom2 = conn.createStatement();
			stmtPom2.execute("SET FOREIGN_KEY_CHECKS=1");
			stmtPom2.close();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		finally {
			if(keys != null)
				keys.close();
		}
		return true;
	}
	
	public static boolean updateBrPrimjeraka(int sifKnjiga, int brPrimjeraka){
		String sqlUpdate = "UPDATE knjiga SET brPrimjeraka = ? WHERE sifKnjiga = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
				) {
			stmt.setInt(1, brPrimjeraka);
			stmt.setInt(2, sifKnjiga);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		return true;
	}
	
	public static void getKnjigaBySifra(int sifra){
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifra);
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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

	public static void getKnjigaByOrigNaslov(String naslov){
		String SQL2 = "SELECT * FROM knjiga WHERE origNaslov = ?";
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, naslov);
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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

	public static void getKnjigaOdAuotra(String autor){
		String[] niz = autor.split(" ");
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN (SELECT sifKnjiga FROM KnjigaAutorRBr WHERE sifAutor IN "
				+ "(SELECT sifAutor FROM autor WHERE imeAutor = ? AND prezAutor = ? "
				+ "GROUP BY sifKnjiga))";	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, niz[1]);
			preparedStatement.setString(2, niz[0]);
			
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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
	
	public static void getKnjigaZaPredmet(String predmet){
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN (SELECT sifKnjiga FROM KnjigaPredmetObaveznost WHERE sifPredmet IN "
				+ "(SELECT sifPredmet FROM predmet WHERE nazPredmet = ?))";	
					
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setString(1, predmet);
			
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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
	
	public static void getObavezneKnjigeZaPredmet(int sifPredmet, int vaznost){
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN (SELECT sifKnjiga FROM KnjigaPredmetObaveznost WHERE sifPredmet = ? AND "
				+ "sifVaznObav IN (SELECT sifVaznObav FROM VaznostObaveznost WHERE sifObaveznost = 1 AND sifVaznost = ?))"; //obaveznost 1 je za DA, 2 za NE	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifPredmet);
			preparedStatement.setInt(2, vaznost);
			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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
	
	public static void getNeObavezneKnjigeZaPredmet(int sifPredmet, int vaznost){
		String SQL2 = "SELECT * FROM knjiga WHERE sifKnjiga IN (SELECT sifKnjiga FROM KnjigaPredmetObaveznost WHERE sifPredmet = ? AND "
				+ "sifVaznObav IN (SELECT sifVaznObav FROM VaznostObaveznost WHERE sifObaveznost = 2 AND sifVaznost = ?))"; //obaveznost 1 je za DA, 2 za NE	
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			dbConnection = DBUtil.getConnection(DBType.MYSQL);
			preparedStatement = dbConnection.prepareStatement(SQL2);
			preparedStatement.setInt(1, sifPredmet);
			preparedStatement.setInt(2, vaznost);

			ResultSet rs = preparedStatement.executeQuery();
			TKnjiga.getListaKnjiga(rs);
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
