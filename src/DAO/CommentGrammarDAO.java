package DAO;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import BEAN.CmtGrammar;


public class CommentGrammarDAO {
	
	public static void InsertCmtGrammar(Connection conn,CmtGrammar cmt,HttpServletRequest request) {
		
		PreparedStatement ps = null;
		String sql = "insert into  webtoeic.cmtgrammar(cmtgrammarcontent,memberid,grammarguidelineid) values(?,?,?) ";
		
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			
			 String cmtgrammarcontent = cmt.getCmtgrammarcontent();
			 int memberid = cmt.getMemberid();
			 int grammarguidelineid = cmt.getGrammarguidelineid();

			 ps.setString(1,cmtgrammarcontent);
			 ps.setInt(2,memberid); 
			 ps.setInt(3,grammarguidelineid);
			 
			 ps.executeUpdate();
			 
			 ps.close();
			 
			
		} catch (SQLException e) {
					request.setAttribute("errorInsertCommentGrammar",e.getMessage());
		}
	}
	
	
	
	
 public static List<CmtGrammar> DisplaylistCmtGrammar(Connection conn, int grammarguidelineid,HttpServletRequest request){
	 
	 List<CmtGrammar> list = new ArrayList<CmtGrammar>();
	 
	 String sql = "select * from cmtgrammar where grammarguidelineid="+grammarguidelineid;
	 
	 try {
		 
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			 
			CmtGrammar cmt = new CmtGrammar();
			
			String cmtgrammarcontent = rs.getString("cmtgrammarcontent");
			int memberid = rs.getInt("memberid");
			
			String fullname = CommentGrammarDAO.DisplayFullNamebyId(conn, memberid);
			
			cmt.setCmtgrammarcontent(cmtgrammarcontent);
			cmt.setMemberid(memberid);;
			cmt.setMembername(fullname);
		
			
			list.add(cmt);
			
		}
		
	} catch (SQLException e) {
		
		request.setAttribute("errorDisplayListCmtGrammar", e.getMessage());
	}
	 
	 return list;
	 
 }
 
 // lấy id tên member theo memberid
 public static String DisplayFullNamebyId(Connection conn, int memberid) {
	 
	 String fullname ="";
	
	 
	 String sql = "SELECT fullname from member,cmtgrammar where member.memberid = cmtgrammar.memberid and member.memberid="+memberid;
	 
	 try {
		 
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		fullname = rs.getString("fullname");
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	 
	 return fullname;
 }
 
 public static int CountComment(Connection conn,int grammarguidelineid,HttpServletRequest request) {
	 
	 int count = 0;
	 
	 String sql =" select count(*) from cmtgrammar where grammarguidelineid="+grammarguidelineid;
	 
	 PreparedStatement ps;
	 
	try {
		
			ps = conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    rs.next();
		    
		    count = rs.getInt(1);
		 
		
	} catch (SQLException e) {
		
		request.setAttribute("errorCountCmtGrammar", e.getMessage());
	}
	 
	 
	 return count;
	 
 }
 
 
 
}
