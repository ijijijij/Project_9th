package cooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection dbConn;
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user="team9th";
			String pw = "tiger";
			String url="jdbc:oracle:thin:@106.10.104.82:1521:orcl";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			
			System.out.println("DB에 연결됐습니다.\n");
		} catch (ClassNotFoundException ce) {
			System.out.println("DB 드라이버 로딩 실패 : "+ce.toString());
		} catch (SQLException se) {
			System.out.println("DB 접속 실패: "+se.toString());
		} catch (Exception e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
		return conn;
	}
}
