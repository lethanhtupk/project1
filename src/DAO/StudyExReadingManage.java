package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Readexercise;
import BEAN.Readquestion;



public class StudyExReadingManage {
	public static List<Readexercise> DisplayListExReading(int start, int count, Connection conn)
	{
		List<Readexercise> list = new ArrayList<Readexercise>();
		
		String sql = "select * from readexercise limit "+(start-1)+", "+count+"";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Readexercise re = new Readexercise();
				
				int readexerciseid = rs.getInt("readexerciseid");
				String readname = rs.getString("readname");
				String readimage = rs.getString("readimage");
				
				re.setReadexeriseid(readexerciseid);
				re.setReadname(readname);
				re.setReadimage(readimage);
				
				
				
				list.add(re);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
			
		
		return list;
	}
	
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		
		String sql = "select count(*) from readexercise";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
	public static List<Readquestion> DisplayDetailExReadingContent( Connection conn, int readexerciseid)
	{
		List<Readquestion> list = new ArrayList<Readquestion>();
		
		String sql = "select * from readquestion where readexerciseid="+readexerciseid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Readquestion rq = new Readquestion();
				
				
				int num = rs.getInt("num");
				String paragraph = rs.getString("paragraph");
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
			
				rq.setNum(num);
				rq.setParagraph(paragraph);
				rq.setQuestion(question);
				rq.setOption1(option1);
				rq.setOption2(option2);
				rq.setOption3(option3);
				rq.setOption4(option4);
				rq.setCorrectanswer(correctanswer);
				rq.setReadexerciseid(readexerciseid);
			
				
				
				list.add(rq);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public static String getNameExReading(int readexerciseid, Connection conn) {
		
		String name = "";
		
		String sql = "select readname from readexercise where readexerciseid="+readexerciseid;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				name = rs.getString("readname");
				
			}
			
			ps.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return name;
	}
	
	
	// count number of A Ex-Reading
	
	public static int CountQuestionExReading(Connection conn,int readexerciseid)
	{
		int count = 0;
		
		
		String sql = "select count(*) from readquestion where readexerciseid="+readexerciseid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	//return list correct answer of Ex-Reading
	
	public static List<Readquestion> ListCorrectAnswer(HttpServletRequest request,Connection conn,int readexerciseid)
	{
		List<Readquestion> list = new ArrayList<Readquestion>();
		
		String sql = "select * from readquestion where readexerciseid= "+readexerciseid;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					Readquestion readquestion = new Readquestion();
					
					int num = rs.getInt("num");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					readquestion.setNum(num);
					readquestion.setQuestion(question);
					readquestion.setOption1(option1);
					readquestion.setOption2(option2);
					readquestion.setOption3(option3);
					readquestion.setOption4(option4);
					readquestion.setCorrectanswer(correctanswer);
					
					list.add(readquestion);
				}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("errorListCorrectAnswer",e.getMessage());
		}
				
		return list;
	}
	
	//hien thi danh sach de thi va phan trang
//	
//	public static List<Readquestion> DisplayDetailExReading(HttpServletRequest request,int start, int count,Connection conn,int readexerciseid)
//	{
//		List<Readquestion> list = new ArrayList<Readquestion>();
//		
//		String sql = "select * from readquestion where readexerciseid= "+readexerciseid+" limit "+(start-1)+", "+count+"";
//		try 
//		{
//			PreparedStatement ptmt = conn.prepareStatement(sql);
//			
//			ResultSet rs = ptmt.executeQuery();
//			
//			
//				while (rs.next())
//				{
//					Readquestion readquestion = new Readquestion();
//					
//					int num = rs.getInt("num");
//					String question = rs.getString("question");
//					String option1 = rs.getString("option1");
//					String option2 = rs.getString("option2");
//					String option3 = rs.getString("option3");
//					String option4 = rs.getString("option4");
//				
//					
//					readquestion.setNum(num);
//					readquestion.setQuestion(question);
//					readquestion.setOption1(option1);
//					readquestion.setOption2(option2);
//					readquestion.setOption3(option3);
//					readquestion.setOption4(option4);
//					
//					readquestion.setReadexerciseid(readexerciseid);
//					
//					list.add(readquestion);
//				}
//			
//		} 
//		catch (SQLException e) 
//		{
//			request.setAttribute("msglambtphandoc",e.getMessage());
//		}
//				
//		return list;
//	}
	
	
	public static int CountNumberQuestionExReading(Connection conn, int readexerciseid)
	{
		int count = 0;
		
		
		String sql = "select count(*) from readquestion where readexerciseid="+readexerciseid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return count;
	}
	
	//xuat dap an dung cua 1 cau hoi trong de thi CorrectAnswer of one question
	
	public static String CorrectAnswerOneQuestion(Connection conn,int readexerciseid, int num)
	{
		String CorrectAnswer="";
		
		String sql = "select correctanswer from readquestion where readexerciseid="+readexerciseid+" and num="+num;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					CorrectAnswer = rs.getString(1);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		return CorrectAnswer;
	}	
}
