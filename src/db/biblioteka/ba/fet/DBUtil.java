package db.biblioteka.ba.fet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "0601";
	private static final String CONN_STRING = "jdbc:mysql://localhost/Biblioteka?useSSL=false";
	
	/**
	 * Konektovanje na BP preko DriverManager-a 
	 */
	public static Connection getConnection(DBType dbType) throws SQLException{
		return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
	}

	/**
	 * Procesuiranje iznimke u slucaju da je konekcija neuspjesna
	 */
	public static void processException(SQLException e){
		System.err.println("Greška: " + e.getMessage());
		System.err.println("Kod greške: " + e.getErrorCode() );
		System.err.println("Reprezentacija greške: " + e.getSQLState());	
	}
}

