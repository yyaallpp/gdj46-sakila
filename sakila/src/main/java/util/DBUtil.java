package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// 중복되는 코드 제거
	// static으로 만들어서 객체를 굳이 만들지 않아도 사용가능하게
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String dburl = "jdbc:mariadb://52.78.198.39:3306/sakila";
			// String dburl = "jdbc:mariadb://localhost:3306/sakila";
			String dbpw = "java1234";
			String dbuser = "root";
			conn = DriverManager.getConnection(dburl,dbuser,dbpw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
