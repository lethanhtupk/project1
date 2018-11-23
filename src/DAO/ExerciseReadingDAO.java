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

import BEAN.Readexercise;
import BEAN.Readquestion;



public class ExerciseReadingDAO {
	
	public static List<Readexercise> DisplayListExerciseReading(Connection conn,HttpServletRequest request){
		List<Readexercise> list = new ArrayList<Readexercise>();
		
		String sql ="select * from readexercise";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Readexercise rx = new Readexercise();
				
				 int readexerciseid = rs.getInt("readexerciseid");
				 String readname = rs.getString("readname");
				 String readimage = rs.getString("readimage");
				 int checkcontent = rs.getInt("checkcontent");
				 
				 rx.setReadexeriseid(readexerciseid);
				 rx.setReadname(readname);
				 rx.setReadimage(readimage);
				 rx.setCheckcontent(checkcontent);
				
				 
				 list.add(rx);
				
			}
					
			
		} catch (SQLException e) {
			
			request.setAttribute("error",e.getMessage());
		}
		
		
		return list;
	}
	
	//Insert name of vocabulary into database
	
	public static boolean InsertExReadingName(HttpServletRequest request, Connection conn,Readexercise rx) {
		
		PreparedStatement ps = null;
		
		String sql = " insert into readexercise(readname) values(?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			String readname = rx.getReadname();
			ps.setString(1,readname);
			
			 ps.executeUpdate();
			//if(kt!= 0)
			return true;
			
			//ps.close();
			
		} catch (SQLException e) {
			
			request.setAttribute("msInsertExReadingName",e.getMessage());
		}
		
		return false;
	}
	
	//get id of Vocabulary name
	
		public static int RetrieveIdExReading(Connection conn,Readexercise rx,HttpServletRequest request) {
			
			int readexerciseid = 0;
			
			PreparedStatement ps = null;
			
			String sql = "Select readexerciseid from readexercise where readname='"+rx.getReadname()+"'"; 
			
			
			try {
				
				
				ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					readexerciseid = rs.getInt("readexerciseid");
					
				}
				
				ps.close();
				rs.close();
				
				
			} catch (SQLException e) {
				
			request.setAttribute("msg", e.getMessage());
			}
			
			
			return readexerciseid;
			
		}
		
		//Add  image for Exercise Reading by Id

		public static void InsertExReadingImage(HttpServletRequest request, Connection conn,String image,int readexerciseid) {
			
		PreparedStatement ps = null;
			
			String sql = "update readexercise set readimage=? where readexerciseid="+readexerciseid;
			
			try {
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1,image);
				
				ps.executeUpdate();
				ps.close();
				
				
			} catch (SQLException e) {
				
				request.setAttribute("msInsertImageExReading",e.getMessage());
			}	
			
		}
		
		// upload file image of Exercise Reading -> save in ImageUpload

		public static String UploadImageExReading(HttpServletRequest request, HttpServletResponse response,int readexerciseid,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";

			ServletContext context = request.getServletContext();
			
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("ImageUpload/");
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
					    			ExerciseReadingDAO.InsertExReadingImage(request, conn, fileName, readexerciseid);
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
		
		
		
		// import content Vocabulary from file excel
		
		public static void ImportExcelExReading(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int readexerciseid) 
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
					
					String paragraph = dataFormatter.formatCellValue(row.getCell(1));	
					String question = dataFormatter.formatCellValue(row.getCell(2));
					String option1 = dataFormatter.formatCellValue(row.getCell(3));
					String option2 = dataFormatter.formatCellValue(row.getCell(4));
					String option3 = dataFormatter.formatCellValue(row.getCell(5));
					String option4 = dataFormatter.formatCellValue(row.getCell(6));
					String correctanswer = dataFormatter.formatCellValue(row.getCell(7));
					
					
					int num;
					if(numstr.equals("")) {
						num = 0;
					}
					else num = Integer.parseInt(numstr);
					
					Readquestion rq = new Readquestion();
					
					rq.setNum(num);
					rq.setParagraph(paragraph);
					rq.setQuestion(question);
					rq.setOption1(option1);
					rq.setOption2(option2);
					rq.setOption3(option3);
					rq.setOption4(option4);
					rq.setCorrectanswer(correctanswer);
					rq.setReadexerciseid(readexerciseid);
					
					
					// insert data vocab to database
	
					ExerciseReadingDAO.InsertDataExReading(request, rq, conn);
				
				
				
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
		
		public static void InsertDataExReading(HttpServletRequest request,Readquestion rq, Connection conn)
		{
			String sql = "insert into readquestion(num,paragraph,question,option1,option2,option3,"
					+ "option4,correctanswer,readexerciseid) values (?,?,?,?,?,?,?,?,?)";
			try 
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1,rq.getNum());
				ps.setString(2,rq.getParagraph());
				ps.setString(3,rq.getQuestion());
				ps.setString(4,rq.getOption1());
				ps.setString(5,rq.getOption2());
				ps.setString(6,rq.getOption3());
				ps.setString(7,rq.getOption4());
				ps.setString(8,rq.getCorrectanswer());
				ps.setInt(9,rq.getReadexerciseid());
				
				ps.executeUpdate();
				ps.close();
				
			} 
			catch (SQLException e) 
			{				
				request.setAttribute("messageInsertDataExReading",e.getMessage());
			}
		}

		
		// add file xlsx to folder: FileExcelVocab
		
		public static String UploadFileExcelExReading(HttpServletRequest request, HttpServletResponse response,int readexerciseid,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";

			ServletContext context = request.getServletContext();
			
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("FileExcelReading/");
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
					    				
					    				ExerciseReadingDAO.ImportExcelExReading(request, response, conn, pathFile, readexerciseid);
					    				
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
		
		//update check content Vocabulary. have -> 1 , don't have -> 0
		
		public static void CheckExReadingContent(HttpServletRequest request,int checkcontent,int readexerciseid,Connection conn) {
			PreparedStatement ps = null;
			String sql = "update readexercise set checkcontent=? where readexerciseid="+readexerciseid;
			
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1,checkcontent);
				ps.executeUpdate();
				
				ps.close();
				
			} catch (SQLException e) {
				
				request.setAttribute("errorCheckExReadingContent", e.getMessage());
			}
			
		}
		

		
		
		

}
