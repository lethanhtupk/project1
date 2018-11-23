package DAO;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import BEAN.GrammarGuideline;
import BEAN.Vocabularyguideline;

public class SearchDAO {

	public static List<GrammarGuideline> DisplayResultSearchGrammar(Connection conn, String grammarname1,HttpServletRequest request){
		
		List<GrammarGuideline> list = new ArrayList<GrammarGuideline>();
		
		String sql = "select * from grammarguideline where grammarname like '%"+grammarname1+"%'";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				
				request.setAttribute("ketqua","Không có kết quả");
				
			}
			
			else {
				
				while(rs.next()) {
					
					GrammarGuideline ggl = new GrammarGuideline();
					 int grammarguidelineid = rs.getInt("grammarguidelineid");
					 String grammarname =rs.getString("grammarname");
					 String grammarimage = rs.getString("grammarimage");
					 
					 ggl.setGrammarimage(grammarimage);
					 ggl.setGrammarguidelineid(grammarguidelineid);
					 ggl.setGrammarname(grammarname);
					 
					 list.add(ggl);
					 
					
					
				}
			}
		} catch (SQLException e) {
			request.setAttribute("ketqua",e.getMessage());
		}
		return list;
		
	}
	
	
	public static List<Vocabularyguideline> DisplayResultSearchVocab(Connection conn, String searchText,HttpServletRequest request){
		
		List<Vocabularyguideline> list = new ArrayList<Vocabularyguideline>();
		
		String sql = "select * from vocabularyguideline where vocabularyname like '%"+searchText+"%'";

		
		try {
			
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				
				request.setAttribute("ketqua","Không có kết quả");
				
			}
			
			
				
				while(rs.next()) {
					
					Vocabularyguideline vocabgl = new Vocabularyguideline();
					 int vocabularyguidelineid = rs.getInt("vocabularyguidelineid");
					 String vocabularyname =rs.getString("vocabularyname");
					 String vocabularyimage = rs.getString("vocabularyimage");
					 
					 vocabgl.setVocabularyguidelineid(vocabularyguidelineid);
					 vocabgl.setVocabularyname(vocabularyname);
					 vocabgl.setVocabularyimage(vocabularyimage);
					 
					 list.add(vocabgl);
					 
					
					
				}
			
		} catch (SQLException e) {
			request.setAttribute("ketqua",e.getMessage());
			
		}
		return list;
		
	}
	
}
