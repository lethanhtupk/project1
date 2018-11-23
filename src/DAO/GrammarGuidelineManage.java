package DAO;

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BEAN.GrammarGuideline;
import DB.DBConnection;

public class GrammarGuidelineManage {

		
public static List<GrammarGuideline> DisplayListGrammarGuideline(Connection conn,HttpServletRequest request){
			List<GrammarGuideline> list = new ArrayList<GrammarGuideline>();
			
			String sql ="select * from grammarguideline";
			
			try {
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				int i = 0;
				while(rs.next()) {
					
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
					 i++;
				}
				
				request.setAttribute("sophantucualist",i);
				
				
				
			} catch (SQLException e) {
				
				request.setAttribute("error",e.getMessage());
			}
			
			
			return list;
		}

public static boolean InsertGrammarguidelineName(HttpServletRequest request, Connection conn,GrammarGuideline grammarguideline) {
	
	PreparedStatement ps = null;
	
	String sql = " insert into grammarguideline(grammarname) values(?)";
	
	try {
		
		ps = conn.prepareStatement(sql);
		String grammarname = grammarguideline.getGrammarname();
		ps.setString(1,grammarname);
		
		 ps.executeUpdate();
		//if(kt!= 0)
		return true;
		
		//ps.close();
		
	} catch (SQLException e) {
		
		request.setAttribute("msinsertGGname",e.getMessage());
	}
	
	return false;
}





public static void UploadFileImageGGline(HttpServletRequest request, HttpServletResponse response,int grammarguidelineid) 
		throws IOException, ServletException {
	
	ServletContext context = request.getServletContext();
	//PrintWriter out = response.getWriter();
	Connection conn = DBConnection.CreateConnect();
	
	//set font de viet tieng viet
	response.setContentType("text/html;charset=UTF-8");
	
	final String Address=context.getRealPath("/ImageUpload");
	final int MaxMemorySize = 1024*1024*3;//3MB
	final int MaxRequestSize = 1024*1024*50;//50MB
	
	
	
	boolean isMultpart = ServletFileUpload.isMultipartContent(request);
	if(!isMultpart) {
		
//		out.println("not have enctypr: multipart/form-data");
		request.setAttribute("msg", "not have enctypr: multipart/form-data");
		
//		test = "not have enctypr: multipart/form-data";
	}
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	// Set factory constrains
	factory.setSizeThreshold(MaxMemorySize);
	
	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	
	//Create a new file upload handler
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	// Set overall request size constraint
	upload.setSizeMax(MaxRequestSize);
	

	try 
	{
		// Parse the request
		List<FileItem> items = upload.parseRequest(request);
		
		// Process the uploaded items
		Iterator<FileItem> iter = items.iterator();
		
		while (iter.hasNext()) 
		{
		    FileItem item = iter.next();

		    if (!item.isFormField()) 
		    {
		    	 String fileName = item.getName();
		    	 
		    	 //pathFile: vá»‹ trÃ­ mÃ  chÃºng ta muá»‘n upload file vÃ o
		    	 //gá»­i cho server
		    	 
		    	 String pathFile = Address + File.separator + fileName;
		    	 
		    	File uploadedFile = new File(pathFile);
		    	boolean kt = uploadedFile.exists();
		    	 
		    	try 
		    	{
		    		if (kt == true)
		    		{
		    			
		    			request.setAttribute("msgrammarguidelineimage","File exists. Please choose file again!");	
		    			request.setAttribute("grammarguidelineid",grammarguidelineid);
		    			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
		    			rd.forward(request, response);
//		    			out.println("File exitst. Require: test file again!");
		    			
		    		}
		    		else
		    		{
		    			item.write(uploadedFile);
//		    			
//		    			request.setAttribute("grammarguidelineid",grammarguidelineid);
//		    			request.setAttribute("msgrammarGGimage","Upload file success");	
		    			
		    			GrammarGuidelineManage.InsertGrammarguidelineImage(request, conn, fileName, grammarguidelineid);
		    			
//		    			List<GrammarGuideline> ggl = GrammarGuidelineManage.DisplayListGrammarGuideline(conn,request);
//		    			
//		    			request.setAttribute("listgrammarguideline",ggl);
		    			
//		    			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineManage.jsp");
//		    			rd.forward(request, response);
		    			
		    			//dÃ¹ng sendRedirect thay vÃ¬ RequestDispatcher Ä‘á»ƒ ko bá»‹ lá»—i font tiáº¿ng viá»‡t.
		    			response.sendRedirect("ListGrammarGMforward");
		    			
		    			
						
//						out.println("Upload file success");
//						out.println(pathFile);
//						out.println(grammarguidelineid);
		    		}
					
					
				} 
		    	catch (Exception e) 
		    	{

		    		request.setAttribute("msgrammarguidelineimage",e.getMessage());
		    		
		    		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
	    			rd.forward(request, response);
		    		//out.println(e.getMessage());
					
				}
		    	 
		    	 
		    } 
		    else 
		    {
		    	request.setAttribute("msgrammarguidelineimage","Upload file failed");
		    	

		    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
    			rd.forward(request, response);
		    	
		    }
		}
		
	} 
	catch (FileUploadException e) 
	{
		
		
		request.setAttribute("msgrammarguidelineimage", e.getMessage());
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
		rd.forward(request, response);
	}
	
//	return test;
	
}


//get id of grammar name
public static int RetrieveIdGrammarGuideline(Connection conn,GrammarGuideline grammarguideline,HttpServletRequest request) {
	
	int grammarguidelineid = 0;
	
	PreparedStatement ps = null;
	
	String sql = "Select grammarguidelineid from grammarguideline where grammarname='"+grammarguideline.getGrammarname()+"'"; 
	
	
	try {
		
		
		ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			grammarguidelineid = rs.getInt("grammarguidelineid");
			
		}
		
		ps.close();
		rs.close();
		
		
	} catch (SQLException e) {
		
	request.setAttribute("msg", e.getMessage());
	}
	
	
	return grammarguidelineid;
	
}


//Add name image by Id

public static void InsertGrammarguidelineImage(HttpServletRequest request, Connection conn,String image,int grammmarguidelineid) {
	
PreparedStatement ps = null;
	
	String sql = "update grammarguideline set grammarimage=? where grammarguidelineid="+grammmarguidelineid;
	
	try {
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1,image);
		
		ps.executeUpdate();
		ps.close();
		
		
	} catch (SQLException e) {
		
		request.setAttribute("msinsertGGname",e.getMessage());
	}
	
	
	
	
}


public static boolean AddContentGrammarGuideline(HttpServletRequest request, Connection conn,GrammarGuideline grammarguideline,int id) {
	

	PreparedStatement ps = null;
	
	String sql = "update grammarguideline set content=? where grammarguidelineid="+id;
	
	try {
		
		ps = conn.prepareStatement(sql);
		String content = grammarguideline.getContent();
		ps.setString(1,content);
		
		
		//if(kt!= 0)
		int kt = ps.executeUpdate();
		
		if(kt!=0) {
			return true;
		}
		 
		
		ps.close();
		
	} catch (SQLException e) {
		
		request.setAttribute("msinsertGGlineContent",e.getMessage());
	}
	
	return false;
	
	
	
}



public static void DeleteGrammarGuideline(Connection conn, int grammarguidelineid,HttpServletRequest request) {
	
String sql ="delete from grammarguideline where grammarguidelineid="+grammarguidelineid;
	
	try {
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.executeUpdate();
		
		ps.close();
		
	} catch (SQLException e) {
		
		request.setAttribute("errordelete1",e.getMessage());
	}
	
	
}
 

public static void DeleteGrammarGLinCmtGrammar(Connection conn, int grammarguidelineid,HttpServletRequest request) {
	
	String sql ="delete from cmtgrammar where grammarguideid="+grammarguidelineid;
	
	try {
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.executeUpdate();
		
		ps.close();
		
	} catch (SQLException e) {
		
		request.setAttribute("errordelete2",e.getMessage());
	}
	
}


}

	



