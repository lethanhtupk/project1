package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.GrammarGuideline;
import BEAN.Vocabularycontent;
import BEAN.Vocabularyguideline;



public class StudyVocabularyManage {

	public static List<Vocabularyguideline> DisplayListGrammar(int start, int count, Connection conn)
	{
		List<Vocabularyguideline> list = new ArrayList<Vocabularyguideline>();
		
		String sql = "select * from vocabularyguideline limit "+(start-1)+", "+count+"";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Vocabularyguideline vgl = new Vocabularyguideline();
				
				int vocabularyguidelineid = rs.getInt("vocabularyguidelineid");
				String vocabularyname = rs.getString("vocabularyname");
				String vocabularyimage = rs.getString("vocabularyimage");
			
				
				vgl.setVocabularyguidelineid(vocabularyguidelineid);
				vgl.setVocabularyname(vocabularyname);
				vgl.setVocabularyimage(vocabularyimage);
				
				
				
				list.add(vgl);
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
		
		
		
		String sql = "select count(*) from vocabularyguideline";
		
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
	public static List<Vocabularycontent> DisplayDetailVocabularyContent( Connection conn, int vocabularyguidelineid)
	{
		List<Vocabularycontent> list = new ArrayList<Vocabularycontent>();
		
		String sql = "select * from vocabularycontent where vocabularyguidelineid="+vocabularyguidelineid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				Vocabularycontent vct = new Vocabularycontent();
				
				int num = rs.getInt("num");
				String vocabularycontentname = rs.getString("vocabularycontentname");
				String transcribe = rs.getString("transcribe");
				String image = rs.getString("image");
				String audiomp3 = rs.getString("audiomp3");
				String audiogg = rs.getString("audiogg");
				String mean = rs.getString("mean");
				String sentence = rs.getString("sentence");
				
				vct.setNum(num);
				vct.setVocabularycontentname(vocabularycontentname);
				vct.setTranscribe(transcribe);
				vct.setImage(image);
				vct.setAudiomp3(audiomp3);
				vct.setAudiogg(audiogg);
				vct.setMean(mean);
				vct.setSentence(sentence);
				
				list.add(vct);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	public static String getNameVocabularyGuideline(int vocabularyguidelineid, Connection conn) {
		
		String name = "";
		
		String sql = "select vocabularyname from vocabularyguideline where vocabularyguidelineid="+vocabularyguidelineid;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				name = rs.getString("vocabularyname");
				
			}
			
			ps.close();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return name;
	}
	
	
}
