package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Parse.ParseGenres;

public class DBConnection {
	public static void main(String[] args) {

//		String url = "jdbc:mysql://localhost:3306/";
//		String dbName = "popcornflavor";
//		String driver = "com.mysql.jdbc.Driver";
//		String userName = "root";
//		String password = "";
//		try {
//			ParseGenres pg = new ParseGenres();
//			pg.parseGenres();
//			Set<String> p = new HashSet<String>();
//			p = pg.getHash();
//			Class.forName(driver).newInstance();
//			Connection conn = DriverManager.getConnection(url + dbName,
//					userName, password);
//			Statement st = conn.createStatement();
//			//int i = 1;
//			for (String s : p) {
//				st.executeUpdate("insert into genre (name) values  ('"
//						+ s + "')");
//			}
//			System.out.println("Insert done");
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
