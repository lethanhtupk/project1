package DAO;

import java.sql.*;
import java.util.*;

import BEAN.GrammarGuideline;

public class StudyGrammarManage {
	
	public static List<GrammarGuideline> DisplayListGrammar(int start, int count, Connection conn)
	{
		List<GrammarGuideline> list = new ArrayList<GrammarGuideline>();
		
		String sql = "select * from grammarguideline limit "+(start-1)+", "+count+"";
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				GrammarGuideline ggl = new GrammarGuideline();
				
				int grammarguidelineid = rs.getInt("grammarguidelineid");
				String grammarname = rs.getString("grammarname");
				String grammarimage = rs.getString("grammarimage");
				String content = rs.getString("content");
				
				ggl.setGrammarguidelineid(grammarguidelineid);
				ggl.setGrammarname(grammarname);
				ggl.setGrammarimage(grammarimage);
				ggl.setContent(content);
				
				
				list.add(ggl);
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
		
		
		
		String sql = "select count(*) from grammarguideline";
		
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
	

	
	public static List<GrammarGuideline> DisplayDetailGrammarContent( Connection conn, int grammarguidelineid)
	{
		List<GrammarGuideline> list = new ArrayList<GrammarGuideline>();
		
		String sql = "select * from grammarguideline where grammarguidelineid="+grammarguidelineid;
		
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next())
			{
				GrammarGuideline ggl = new GrammarGuideline();
				
//				int grammarguidelineid = rs.getInt("grammarguidelineid");
				
				String grammarname = rs.getString("grammarname");
				String grammarimage = rs.getString("grammarimage");
				String content = rs.getString("content");
				
				ggl.setGrammarguidelineid(grammarguidelineid);
				ggl.setGrammarname(grammarname);
				ggl.setGrammarimage(grammarimage);
				ggl.setContent(content);
				
				
				list.add(ggl);
			}
				
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	

}
