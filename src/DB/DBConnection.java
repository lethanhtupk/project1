package DB;
import java.sql.*;

public class DBConnection {
	 public static Connection CreateConnect(){
		 
		 // khai bao ket noi
		 Connection conn = null;
	
		 String url = "jdbc:mysql://localhost:3306/webtoeic?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";
		 String username = "root";
		 String password = "cuopbien123";
		 
		 // load driver
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 
		 // tao ket noi 
		 try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e){
			e.printStackTrace();
		}
		 return conn;
		 
	 }
}
