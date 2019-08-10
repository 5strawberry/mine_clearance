package library.com.util;

import java.sql.Connection;
import java.sql.DriverManager;

//import com.mysql.jdbc.Connection;

public class DbUtil {
	private String dbUrl = "jdbc:mysql://localhost:3306/one?serverTimezone=UTC";
	private String dbUserName = "root";
	private String dbPassword = "wuxiao123";
	//private String jdbcName = "com.mysql.jdbc.Driver";
	//com.mysql.cj.jdbc.driver
	private String jdbcName = "com.mysql.cj.jdbc.Driver";
	/*
	 * 数据库连接
	 */
	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		
		Connection con = (Connection) DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
		
	}
	
	/*try {
		con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
	}catch(SQLException e) {
		System.out.println(e);
	}*/
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}