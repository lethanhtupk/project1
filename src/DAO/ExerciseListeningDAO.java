package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import BEAN.Listenexercise;
import BEAN.Listenquestion;



public class ExerciseListeningDAO {

	
	public static List<Listenexercise> DisplayListExerciseListening(Connection conn,HttpServletRequest request){
		List<Listenexercise> list = new ArrayList<Listenexercise>();
		
		String sql ="select * from listenexercise";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Listenexercise le = new Listenexercise();
				
				 int listenexerciseid = rs.getInt("listenexerciseid");
				 String listenexercisename = rs.getString("listenexercisename");
				 String listenexerciseimage = rs.getString("listenexerciseimage");
				 int checkcontent = rs.getInt("checkcontent");
				 
				 le.setListenexerciseid(listenexerciseid);
				 le.setListenexerciseimage(listenexerciseimage);
				 le.setListenexercisename(listenexercisename);
				 le.setCheckcontent(checkcontent);
				
				 list.add(le);
				
			}
					
			
		} catch (SQLException e) {
			
			request.setAttribute("error",e.getMessage());
		}
		
		
		return list;
	}
	
	//Insert name of Ex-Listening into database
	
	public static boolean InsertExListeningName(HttpServletRequest request, Connection conn,Listenexercise le) {
		
		PreparedStatement ps = null;
		
		String sql = " insert into listenexercise(listenexercisename) values(?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			String listenexercisename = le.getListenexercisename();
			ps.setString(1,listenexercisename);
			
			 ps.executeUpdate();
			//if(kt!= 0)
			return true;
			
			//ps.close();
			
		} catch (SQLException e) {
			
			request.setAttribute("msInsertExListeningName",e.getMessage());
		}
		
		return false;
	}
	
	//get id of Ex-Listening name
	
		public static int RetrieveIdListening(Connection conn,Listenexercise le,HttpServletRequest request) {
			
			int listenexerciseid = 0;
			
			PreparedStatement ps = null;
			
			String sql = "Select listenexerciseid from listenexercise where listenexercisename='"+le.getListenexercisename()+"'"; 
			
			
			try {
				
				
				ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					listenexerciseid = rs.getInt("listenexerciseid");
					
				}
				
				ps.close();
				rs.close();
				
				
			} catch (SQLException e) {
				
			request.setAttribute("msg", e.getMessage());
			}
			
			
			return listenexerciseid;
			
		}
		
		//Add  image for Exercise Listening by Id

		public static void InsertExListeningImage(HttpServletRequest request, Connection conn,String image,int listenexerciseid) {
			
		PreparedStatement ps = null;
			
			String sql = "update listenexercise set listenexerciseimage=? where listenexerciseid="+listenexerciseid;
			
			try {
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1,image);
				
				ps.executeUpdate();
				ps.close();
				
				
			} catch (SQLException e) {
				
				request.setAttribute("msInsertImageExListening",e.getMessage());
			}	
			
		}
		
		// upload file image of Exercise Listening -> save in ImageAudioExListening

		public static String UploadImageExListening(HttpServletRequest request, HttpServletResponse response,int listenexerciseid,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";

			ServletContext context = request.getServletContext();
			
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("ImageAudioExListening/");
			final int MaxMemorySize = 1024*1024*3;//3MB
			final int MaxRequestSize = 1024*1024*50;//50MB
			
			
			
			boolean isMultpart = ServletFileUpload.isMultipartContent(request);
			if(!isMultpart) {
				test = "not have enctypr: multipart/form-data";
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
				    	 
				    	 String pathFile = Address + File.separator + fileName;
				    	 
				    	File uploadedFile = new File(pathFile);
				    	
				    	boolean kt = uploadedFile.exists();
				    	 
				    	try 
				    	{
				    		if (kt == true)
				    		{
				    			
				    			test = "File exists. Please choose file again!";
			    			
				    		}
				    		else
				    		{
				    			item.write(uploadedFile);
				    			
					    			try
					    			{
					    			
					    			ExerciseListeningDAO.InsertExListeningImage(request, conn, fileName, listenexerciseid);
					    			}
					    			catch(NullPointerException e)
					    			{
					    				test = e.getMessage();
					    			}
					    			
					    			test="Success";
				    		}	
						} 
				    	catch (Exception e) 
				    	{
				    		test = e.getMessage();
						}
				    } 
				    else 
				    {
				    	test ="Upload file failed";

				    }
				}
				
			} 
			catch (FileUploadException e) 
			{
				test = e.getMessage();
			}
			
			return test;
		}
		
		
		
		// import content Ex-Listening from file excel
		
		public static void ImportExcelExListening(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int listenexerciseid) 
				throws ServletException, IOException
		{
			
			FileInputStream fis;
			try 
			{
				fis = new FileInputStream(address);
				
				HSSFWorkbook hof = new HSSFWorkbook(fis);
				
				Iterator<Sheet> inIteratorSheet = hof.sheetIterator();
				
				Sheet sheet = hof.getSheetAt(0);
				
				DataFormatter dataFormatter = new DataFormatter();
				
				for(Row row : sheet) {
					
					String numstr = dataFormatter.formatCellValue(row.getCell(0));
					
					String imagename = dataFormatter.formatCellValue(row.getCell(1));	
					String audiomp3 = dataFormatter.formatCellValue(row.getCell(2));
					String audiogg = dataFormatter.formatCellValue(row.getCell(3));
					String question = dataFormatter.formatCellValue(row.getCell(4));
					String option1 = dataFormatter.formatCellValue(row.getCell(5));
					String option2 = dataFormatter.formatCellValue(row.getCell(6));
					String option3 = dataFormatter.formatCellValue(row.getCell(7));
					String option4 = dataFormatter.formatCellValue(row.getCell(8));
					String correctanswer = dataFormatter.formatCellValue(row.getCell(9));
				
					
					
					int num;
					if(numstr.equals("")) {
						num = 0;
					}
					else num = Integer.parseInt(numstr);
					
					Listenquestion le = new Listenquestion();
					
					
					le.setNum(num);
					le.setImagename(imagename);
					le.setAudiomp3(audiomp3);
					le.setAudiogg(audiogg);
					le.setQuestion(question);
					le.setOption1(option1);
					le.setOption2(option2);
					le.setOption3(option3);
					le.setOption4(option4);
					le.setCorrectanswer(correctanswer);
					le.setListenexerciseid(listenexerciseid);
					
					
					
					// insert data listen question to database
					ExerciseListeningDAO.InsertDataExListening(request, le, conn);
				
				    } 
				    
				
				
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				
			}		
}
		
		
		// insert data from file excel to database
		
		public static void InsertDataExListening(HttpServletRequest request,Listenquestion lq, Connection conn)
		{
			String sql = "insert into listenquestion(num,imagename,audiomp3,audiogg,question,option1,"
					+ "option2,option3,option4,correctanswer,listenexerciseid) values (?,?,?,?,?,?,?,?,?,?,?)";
			try 
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1,lq.getNum());
				ps.setString(2,lq.getImagename());
				ps.setString(3,lq.getAudiomp3());
				ps.setString(4,lq.getAudiogg());
				ps.setString(5,lq.getQuestion());
				ps.setString(6,lq.getOption1());
				ps.setString(7,lq.getOption2());
				ps.setString(8,lq.getOption3());
				ps.setString(9,lq.getOption4());
				ps.setString(10,lq.getCorrectanswer());
				
				ps.setInt(11,lq.getListenexerciseid());
				
				ps.executeUpdate();
				ps.close();
				
			} 
			catch (SQLException e) 
			{				
				request.setAttribute("messageInsertDataExListening",e.getMessage());
			}
		}

		
		// add file xlsx to folder: FileExcelVocab
		
		public static String UploadFileExcelExListening(HttpServletRequest request, HttpServletResponse response,int listenexerciseid,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";

			ServletContext context = request.getServletContext();
			
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("FileExcelListening/");
			final int MaxMemorySize = 1024*1024*3;//3MB
			final int MaxRequestSize = 1024*1024*50;//50MB
			
			
			
			boolean isMultpart = ServletFileUpload.isMultipartContent(request);
			if(!isMultpart) {
				//request.setAttribute("msg", "");
				test = "not have enctypr: multipart/form-data";
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
				    	 
				    	 String pathFile = Address + File.separator + fileName;
				    	 
				    	File uploadedFile = new File(pathFile);
				    	
				    	boolean kt = uploadedFile.exists();
				    	 
				    	try 
				    	{
				    		if (kt == true)
				    		{
				    			
				    			test = "File exists. Please choose file again!";
			    			
				    		}
				    		else
				    		{
				    			item.write(uploadedFile);
				    			
					    			try
					    			{
					    				//add data to database
					    				
					    		
					    				ExerciseListeningDAO.ImportExcelExListening(request, response, conn, pathFile, listenexerciseid);
					    			
					    				
					    			}
					    			catch(NullPointerException e)
					    			{
					    				test = e.getMessage();
					    			}
					    			
					    			test="Success";
				    		}	
						} 
				    	catch (Exception e) 
				    	{
				    		test = e.getMessage();
						}
				    } 
				    else 
				    {
				    	test ="Upload file failed";

				    }
				}
				
			} 
			catch (FileUploadException e) 
			{
				test = e.getMessage();
			}
			
			return test;
		}
		
		//update check content Listen exercise. have -> 1 , don't have -> 0
		
		public static void CheckExListeningContent(HttpServletRequest request,int checkcontent,int listenexerciseid,Connection conn) {
			PreparedStatement ps = null;
			String sql = "update listenexercise set checkcontent=? where listenexerciseid="+listenexerciseid;
			
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1,checkcontent);
				ps.executeUpdate();
				
				ps.close();
				
			} catch (SQLException e) {
				
				request.setAttribute("errorCheckExListeningContent", e.getMessage());
			}
			
		}
	
		// Add Audio-Image for Ex-Listening, save in ImageAudioExListening
		
		public static String AddAudioImageExListening(HttpServletRequest request, HttpServletResponse response,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("ImageAudioExListening/");
			final int MaxMemorySize = 1024*1024*3;//3MB
			final int MaxRequestSize = 1024*1024*50;//50MB
			
			boolean isMultpart = ServletFileUpload.isMultipartContent(request);
			if(!isMultpart) {
				test = "not have enctypr: multipart/form-data";
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
				    	 String pathFile = Address + File.separator + fileName;
				    	File uploadedFile = new File(pathFile);
				    	
				    	boolean kt = uploadedFile.exists();
				    	 
				    	try 
				    	{
				    		if (kt == true)
				    		{
				    			
				    			test = "File exists. Please choose file again!";
			    			
				    		}
				    		else
				    		{
				    			item.write(uploadedFile);
				    			
					    		test="Success";
				    		}	
						} 
				    	catch (Exception e) 
				    	{
				    		test = e.getMessage();
						}
				    } 
				    else 
				    {
				    	test ="Upload file failed";

				    }
				}
				
			} 
			catch (FileUploadException e) 
			{
				test = e.getMessage();
			}
			
			return test;
		}
	
}
