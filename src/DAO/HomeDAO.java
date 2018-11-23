package DAO;

import java.sql.*;
import java.util.*;

import BEAN.Slidebanner;

public class HomeDAO {
	
	public static List<Slidebanner> DisplaySlidebanner(Connection conn){
		List<Slidebanner> list = new ArrayList<Slidebanner>();
		
		String sql ="select * from slidebanner";
		
		try {
			
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				
				Slidebanner slide = new Slidebanner();
				
				 int slidebannerid = rs.getInt("slidebannerid");
				 String slidename = rs.getString("slidename");
				 String slidecontent = rs.getString("slidecontent");
				 String slideimage = rs.getString("slideimage");
				 
				 slide.setSlidebannerid(slidebannerid);
				 slide.setSlidename(slidename);
				 slide.setSlidecontent(slidecontent);
				 slide.setSlideimage(slideimage);
				 
				 list.add(slide);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return list;
	}

}
