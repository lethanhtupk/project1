package DAO;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;

public class RegisterDAO {
	
	public static boolean InsertMember(HttpServletRequest request,Connection conn,Member mb) {
		
		PreparedStatement ps = null;
		String sql ="insert into member(membername,memberpass,fullname,categorymemberid) values(?,?,?,?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			String membername = mb.getMembername();
			String memberpass = mb.getMemberpass();
			String fullname = mb.getFullname();
			int categorymemberid = 1;
			
			ps.setString(1,membername);
			ps.setString(2,memberpass);
			ps.setString(3,fullname);
			ps.setInt(4,categorymemberid);
			
			int kt = ps.executeUpdate();
			
			if(kt != 0) {
				return true;
			}
			
			ps.close();
			
		} catch (SQLException e) {
			request.setAttribute("msgregister",e.getMessage());
			
			
		}
		return false;
	}

}
