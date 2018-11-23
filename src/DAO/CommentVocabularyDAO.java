package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.CmtVocabulary;



public class CommentVocabularyDAO {

public static void InsertCmtVocab(Connection conn,CmtVocabulary cmtVocab,HttpServletRequest request) {
		
		PreparedStatement ps = null;
		String sql = "insert into  webtoeic.cmtvocabulary(cmtvocabularycontent,memberid,vocabularyguidelineid) values(?,?,?) ";
		
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			
			 String cmtvocabularycontent = cmtVocab.getCmtvocabularycontent();
			 int memberid = cmtVocab.getMemberid();
			 int vocabularyguidelineid = cmtVocab.getVocabularyguidelineid();

			 ps.setString(1,cmtvocabularycontent);
			 ps.setInt(2,memberid); 
			 ps.setInt(3,vocabularyguidelineid);
			 
			 ps.executeUpdate();
			 
			 ps.close();
			 
			
		} catch (SQLException e) {
					request.setAttribute("errorInsertCommentVocab",e.getMessage());
		}
	}
	
	
	
	
 public static List<CmtVocabulary> DisplaylistCmtVocabulary(Connection conn, int vocabularyguidelineid){
	 
	 List<CmtVocabulary> list = new ArrayList<CmtVocabulary>();
	 
	 String sql = "select * from cmtvocabulary where vocabularyguidelineid="+vocabularyguidelineid;
	 
	 try {
		 
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			 
			CmtVocabulary cmtVocab = new CmtVocabulary();
			
			String cmtvocabularycontent = rs.getString("cmtvocabularycontent");
			int memberid = rs.getInt("memberid");
			
			String fullname = CommentGrammarDAO.DisplayFullNamebyId(conn, memberid);
			
			cmtVocab.setCmtvocabularycontent(cmtvocabularycontent);
			cmtVocab.setMemberid(memberid);;
			cmtVocab.setMembername(fullname);
		
			
			list.add(cmtVocab);
			
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	 
	 return list;
	 
 }
 
 
 public static String DisplayFullNamebyId(Connection conn, int memberid) {
	 String fullname ="";
	 
	// String sql = "select name from member where memberid="+memberid ;
	 
	 String sql = "SELECT fullname from member,cmtvocabulary where member.memberid = cmtvocabulary.memberid and member.memberid="+memberid;
	 
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
 
 public static int CountComment(Connection conn,int vocabularyguidelineid) {
	 int count = 0;
	 
	 String sql =" select count(*) from cmtvocabulary where vocabularyguidelineid="+vocabularyguidelineid;
	 
	 PreparedStatement ps;
	try {
		
			ps = conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    rs.next();
		    
		    count = rs.getInt(1);
		 
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	 
	
	  return count;
	 
 }
 
 
	
	
}
