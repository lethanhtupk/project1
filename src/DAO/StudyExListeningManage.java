package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Listenexercise;
import BEAN.Listenquestion;



public class StudyExListeningManage {
	
	public static List<Listenexercise> DisplayListExListening(int start, int count, Connection conn)
	{
		List<Listenexercise> list = new ArrayList<Listenexercise>();
		
		String sql = "select * from listenexercise limit "+(start-1)+", "+count+"";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Listenexercise le = new Listenexercise();
				
				int listenexerciseid = rs.getInt("listenexerciseid");
				String listenexercisename = rs.getString("listenexercisename");
				String listenexerciseimage = rs.getString("listenexerciseimage");
				
				le.setListenexerciseid(listenexerciseid);
				le.setListenexercisename(listenexercisename);
				le.setListenexerciseimage(listenexerciseimage);
				
				
				list.add(le);
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
		
		
		
		String sql = "select count(*) from listenexercise";
		
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
	public static List<Listenquestion> DisplayDetailListeningContent( Connection conn, int listenexerciseid)
	{
		List<Listenquestion> list = new ArrayList<Listenquestion>();
		
		String sql = "select * from listenquestion where listenexerciseid="+listenexerciseid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Listenquestion lq = new Listenquestion();
				
				
				int num = rs.getInt("num");
				String imagename = rs.getString("imagename");
				String audiomp3 = rs.getString("audiomp3");
				String audiogg = rs.getString("audiogg");
				String question = rs.getString("question");
				String  option1= rs.getString("option1");
				String  option2 = rs.getString("option2");
				String  option3= rs.getString("option3");
				String  option4= rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
			
				lq.setNum(num);
				lq.setImagename(imagename);
				lq.setAudiomp3(audiomp3);
				lq.setAudiogg(audiogg);
				lq.setQuestion(question);
				lq.setOption1(option1);
				lq.setOption2(option2);
				lq.setOption3(option3);
				lq.setOption4(option4);
				lq.setCorrectanswer(correctanswer);
				lq.setListenexerciseid(listenexerciseid);
				
			
				list.add(lq);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public static String getNameExListening(int listenexerciseid, Connection conn) {
		
		String name = "";
		
		String sql = "select readname from listenexercise where listenexerciseid="+listenexerciseid;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				name = rs.getString("listenexercisename");
				
			}
			
			ps.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return name;
	}
	
	
	// count number of A Ex-Listening
	
	public static int CountQuestionExListening(Connection conn,int listenexerciseid)
	{
		int count = 0;
		
		
		String sql = "select count(*) from listenquestion where listenexerciseid="+listenexerciseid;
		
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
	
	//return list correct answer of Ex-Listening
	
	public static List<Listenquestion> ListCorrectAnswer(HttpServletRequest request,Connection conn,int listenexerciseid,int num)
	{
			List<Listenquestion> list = new ArrayList<Listenquestion>();
		
			String sql = "select * from listenquestion where listenexerciseid= "+listenexerciseid+" and num="+num;
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					Listenquestion listenquestion = new Listenquestion();
					
					int num1 = rs.getInt("num");
					String imagename  = rs.getString("imagename");
					String audiomp3  = rs.getString("audiomp3");
					String audiogg  = rs.getString("audiogg");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					listenquestion.setNum(num1);
					listenquestion.setImagename(imagename);
					listenquestion.setAudiomp3(audiomp3);
					listenquestion.setAudiogg(audiogg);
					listenquestion.setQuestion(question);
					listenquestion.setOption1(option1);
					listenquestion.setOption2(option2);
					listenquestion.setOption3(option3);
					listenquestion.setOption4(option4);
					listenquestion.setCorrectanswer(correctanswer);
					
					list.add(listenquestion);
				}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
				
		return list;
	}
	
	//hien thi danh sach de thi va phan trang
	
	public static List<Listenquestion> DisplayDetailExListening(HttpServletRequest request,int start, int count,Connection conn,int listenexerciseid)
	{
		List<Listenquestion> list = new ArrayList<Listenquestion>();
		
		String sql = "select * from listenquestion where listenexerciseid= "+listenexerciseid+" limit "+(start-1)+", "+count+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			
				while (rs.next())
				{
					Listenquestion listenquestion = new Listenquestion();
					
					int num = rs.getInt("num");
					String imagename = rs.getString("imagename");
					String audiomp3 = rs.getString("audiomp3");
					String audiogg = rs.getString("audiogg");
					String question = rs.getString("question");
					String option1 = rs.getString("option1");
					String option2 = rs.getString("option2");
					String option3 = rs.getString("option3");
					String option4 = rs.getString("option4");
					String correctanswer = rs.getString("correctanswer");
					
					listenquestion.setNum(num);
					listenquestion.setImagename(imagename);
					listenquestion.setAudiomp3(audiomp3);
					listenquestion.setAudiogg(audiogg);
					listenquestion.setQuestion(question);
					listenquestion.setOption1(option1);
					listenquestion.setOption2(option2);
					listenquestion.setOption3(option3);
					listenquestion.setOption4(option4);
					listenquestion.setCorrectanswer(correctanswer);
					
					listenquestion.setListenexerciseid(listenexerciseid);

					
					list.add(listenquestion);
				}
			
		} 
		catch (SQLException e) 
		{
			request.setAttribute("msgDisplayDetailExListening",e.getMessage());
		}
				
		return list;
	}
	
	
	
	//xuat dap an dung cua 1 cau hoi trong de thi CorrectAnswer of one question
	
	public static String CorrectAnswerOneQuestion(Connection conn,int listenexerciseid, int num)
	{
		String CorrectAnswer="";
		
		String sql = "select correctanswer from listenquestion where listenexerciseid="+listenexerciseid+" and num="+num;
		
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
