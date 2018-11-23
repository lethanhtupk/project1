package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Examination;
import BEAN.Examinationquestion;


public class DoingFullExamToeicDAO {

	public static List<Examination> DisplayListExamination(int start, int count, Connection conn,HttpServletRequest request)
	{
		List<Examination> list = new ArrayList<Examination>();
		
		String sql = "select * from examination limit "+(start-1)+", "+count+"";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Examination ex = new Examination();
				
				int examinationid = rs.getInt("examinationid");
				String examinationname = rs.getString("examinationname");
				String examinationimage = rs.getString("examinationimage");
				int checkexamination = rs.getInt("checkexamination");
				
				ex.setExaminationid(examinationid);
				ex.setExaminationname(examinationname);
				ex.setExaminationimage(examinationimage);
				ex.setCheckexamination(checkexamination);
				
				list.add(ex);
			}
				
		} 
		catch (SQLException e) 
		{
			
			request.setAttribute("msgDisplayListExamination",e.getMessage());
		}
		
		
		
		return list;
	}
	
	public static int Countrow(Connection conn)
	{
		int count = 0;
		
		
		
		String sql = "select count(*) from examination";
		
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
	

	
	
	public static List<Examinationquestion> DisplayDetailExamination (Connection conn,HttpServletRequest request,int examinationid)
	{
		List<Examinationquestion> list = new ArrayList<Examinationquestion>();
		
		String sql = "select * from examinationquestion where examinationid="+examinationid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Examinationquestion ex = new Examinationquestion();
				
				int examinationquestionid = rs.getInt("examinationquestionid");
				int num = rs.getInt("num");
				String imagequestion = rs.getString("imagequestion");
				String audiogg = rs.getString("audiogg");
				String audiomp3 = rs.getString("audiomp3");
				String paragraph = rs.getString("paragraph");
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
				
				ex.setExaminationquestionid(examinationquestionid);
				ex.setNum(num);
				ex.setImagequestion(imagequestion);
				ex.setAudiogg(audiogg);
				ex.setAudiomp3(audiomp3);
				ex.setParagraph(paragraph);
				ex.setQuestion(question);
				ex.setOption1(option1);
				ex.setOption2(option2);
				ex.setOption3(option3);
				ex.setOption4(option4);
				ex.setCorrectanswer(correctanswer);
				ex.setExaminationid(examinationid);
				
				
				list.add(ex);
			}
				
		} 
		catch (SQLException e) 
		{
			
			request.setAttribute("msgDisplayDetailExam",e.getMessage());
		}
		
		
		
		return list;
	}
}
