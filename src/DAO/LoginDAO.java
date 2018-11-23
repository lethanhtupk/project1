package DAO;


import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;

public class LoginDAO {
	
	
	public static boolean checkMember(Connection conn,HttpServletRequest request,String membername, String memberpass) {
		
//		if(request.getCharacterEncoding()==null) {
//			try {
//				request.setCharacterEncoding("UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
		PreparedStatement ps = null;
	
		String sql = "Select * from member Where membername='" + membername + "' and memberpass='" + memberpass + "'";
		
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			int count = 0;
			
			while (rs.next()) {
				
				count++;
			
			}
			
			if(count == 0) {
				 request.setAttribute("msglogin","Tên tài khoản hoặc mật khẩu không chính xác");
				
//				 request.setAttribute("msglogin2", memberpass);
				 
			}
			
			if(count == 1) {
				//check phÃ¢n quyá»�n
				int categorymemberid = LoginDAO.Authorizationmember(request, conn, membername, memberpass) ;
				//request.setAttribute(arg0, arg1);
				request.setAttribute("msglogin","Đăng nhập thành công");
				
				
				
				return true;
			}
			
			
		} catch (SQLException e) {
			
			request.setAttribute("msglogin",e.getMessage());
		}
		
		
		return false;
	}
	
	
	
	
	public static int Authorizationmember(HttpServletRequest request, Connection conn, String membername, String memberpass)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "select categorymemberid from member where membername='"+membername+"' AND memberpass='"+memberpass+"'";
		int categorymemberid = 1;
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				categorymemberid = rs.getInt("categorymemberid");		
			}
			
			ptmt.close();
			rs.close();
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msglogin",e.getMessage());
		}
		
		return categorymemberid;
	}
	
	
	
	
	public static String getFullname(Connection conn,HttpServletRequest request,String membername, String memberpass) {
		
		String fullname= null;
		
		PreparedStatement ps = null;
		
		String sql = "Select fullname from member Where membername='" + membername + "' and memberpass='" + memberpass + "'";
	
		
		try {
			
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
			fullname=rs.getString("fullname");
			
			}
			
			
		} catch (SQLException e) {
			
			request.setAttribute("msglogin",e.getMessage());
		}
		
		
		
		return fullname;
	}
	
public static int GetMemberid(Connection conn,HttpServletRequest request,String membername, String memberpass) {
	int id = 0;
	
	PreparedStatement ps = null;
	
	String sql = "Select memberid from member Where membername='" + membername + "' and memberpass='" + memberpass + "'";

	
	try {
		
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
		id=rs.getInt("memberid");
		
		}
		
		
	} catch (SQLException e) {
		
		request.setAttribute("msglogin",e.getMessage());
	}
	
	
	
	
	return id;
}

}
