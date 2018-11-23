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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.Vocabularycontent;
import BEAN.Vocabularyguideline;
import DB.DBConnection;



public class VocabularyManageDAO {
	
	public static List<Vocabularyguideline> DisplayListVocabulary(Connection conn,HttpServletRequest request){
		List<Vocabularyguideline> list = new ArrayList<Vocabularyguideline>();
		
		String sql ="select * from vocabularyguideline";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Vocabularyguideline vocab = new Vocabularyguideline();
				
				 int vocabularyguidelineid = rs.getInt("vocabularyguidelineid");
				 String vocabularyname = rs.getString("vocabularyname");
				 String vocabularyimage = rs.getString("vocabularyimage");
				 int checkcontent = rs.getInt("checkcontent");
				 
				 vocab.setVocabularyguidelineid(vocabularyguidelineid);
				 vocab.setVocabularyname(vocabularyname);
				 vocab.setVocabularyimage(vocabularyimage);
				 vocab.setCheckcontent(checkcontent);
				
				 
				 list.add(vocab);
				
			}
					
			
		} catch (SQLException e) {
			
			request.setAttribute("error",e.getMessage());
		}
		
		
		return list;
	}
	
	//Insert name of vocabulary into database
	
	public static boolean InsertVocabularyName(HttpServletRequest request, Connection conn,Vocabularyguideline vocab) {
		
		PreparedStatement ps = null;
		
		String sql = " insert into vocabularyguideline(vocabularyname) values(?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			String vocabularyname = vocab.getVocabularyname();
			ps.setString(1,vocabularyname);
			
			 ps.executeUpdate();
			//if(kt!= 0)
			return true;
			
			//ps.close();
			
		} catch (SQLException e) {
			
			request.setAttribute("msInsertVocabName",e.getMessage());
		}
		
		return false;
	}
	
	//get id of Vocabulary name
	
		public static int RetrieveIdVocabulary(Connection conn,Vocabularyguideline vocab,HttpServletRequest request) {
			
			int vocabularyguidelineid = 0;
			
			PreparedStatement ps = null;
			
			String sql = "Select vocabularyguidelineid from vocabularyguideline where vocabularyname='"+vocab.getVocabularyname()+"'"; 
			
			
			try {
				
				
				ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					vocabularyguidelineid = rs.getInt("vocabularyguidelineid");
					
				}
				
				ps.close();
				rs.close();
				
				
			} catch (SQLException e) {
				
			request.setAttribute("msg", e.getMessage());
			}
			
			
			return vocabularyguidelineid;
			
		}
		
		//Add  image for Vocabulary by Id

		public static void InsertVocabularyImage(HttpServletRequest request, Connection conn,String image,int vocabularyguidelineid) {
			
		PreparedStatement ps = null;
			
			String sql = "update vocabularyguideline set vocabularyimage=? where vocabularyguidelineid="+vocabularyguidelineid;
			
			try {
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1,image);
				
				ps.executeUpdate();
				ps.close();
				
				
			} catch (SQLException e) {
				
				request.setAttribute("msInsertImageVocabulary",e.getMessage());
			}	
			
		}
		
		// upload file image of Vocabulary -> save in ImageAudioVocab 

		public static String UploadImageVocabulary(HttpServletRequest request, HttpServletResponse response,int vocabularyguidelineid,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";

			ServletContext context = request.getServletContext();
			
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("ImageAudioVocab/");
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
					    				VocabularyManageDAO.InsertVocabularyImage(request, conn, fileName, vocabularyguidelineid);
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
		
		public static void ImportExcelVocab(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int vocabularyguidelineid) 
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
					
					String vocabularycontentname = dataFormatter.formatCellValue(row.getCell(1));	
					String transcribe = dataFormatter.formatCellValue(row.getCell(2));
					String image = dataFormatter.formatCellValue(row.getCell(3));
					String audiomp3 = dataFormatter.formatCellValue(row.getCell(4));
					String audiogg = dataFormatter.formatCellValue(row.getCell(5));
					String mean = dataFormatter.formatCellValue(row.getCell(6));
					String sentence = dataFormatter.formatCellValue(row.getCell(7));
					
					
					int num;
					if(numstr.equals("")) {
						num = 0;
					}
					else num = Integer.parseInt(numstr);
					
					Vocabularycontent vocab = new Vocabularycontent();
					
					vocab.setNum(num);
					vocab.setVocabularycontentname(vocabularycontentname);
					vocab.setTranscribe(transcribe);
					vocab.setImage(image);
					vocab.setAudiomp3(audiomp3);
					vocab.setAudiogg(audiogg);
					vocab.setMean(mean);
					vocab.setSentence(sentence);
					vocab.setVocabularyguidelineid(vocabularyguidelineid);
					
					
					// insert data vocab to database
					VocabularyManageDAO.InsertDataVocab(request, vocab, conn);
				
				
				
//				 int starRow = sheet.getFirstRowNum();
//				    int endRow = sheet.getLastRowNum();
//
//				    for (int i = starRow ; i <= endRow; i++) {
//				       
//				    	Vocabularycontent vocab = new Vocabularycontent();
//				    	
//				        Cell c1 = hof.getSheetAt(0).getRow(i).getCell(1);
//				        String numstr = c1.toString();
//				        int num = Integer.parseInt(numstr);
//				        vocab.setNum(num);
//
//				        Cell c2 = hof.getSheetAt(0).getRow(i).getCell(2);
//				        vocab.setVocabularycontentname(c2.toString());
//				        
//				        Cell c3 = hof.getSheetAt(0).getRow(i).getCell(3);
//				        vocab.setTranscribe(c3.toString());
//				        
//				        
//				        
//				       
//				        VocabularyManageDAO.InsertDataVocab(request, vocab, conn);
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
		
		public static void InsertDataVocab(HttpServletRequest request,Vocabularycontent vocab, Connection conn)
		{
			String sql = "insert into vocabularycontent(num,vocabularycontentname,transcribe,image,audiomp3,audiogg,"
					+ "mean,sentence,vocabularyguidelineid) values (?,?,?,?,?,?,?,?,?)";
			try 
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1,vocab.getNum());
				ps.setString(2,vocab.getVocabularycontentname());
				ps.setString(3,vocab.getTranscribe());
				ps.setString(4,vocab.getImage());
				ps.setString(5,vocab.getAudiomp3());
				ps.setString(6,vocab.getAudiogg());
				ps.setString(7,vocab.getMean());
				ps.setString(8,vocab.getSentence());
				ps.setInt(9,vocab.getVocabularyguidelineid());
				
				ps.executeUpdate();
				ps.close();
				
			} 
			catch (SQLException e) 
			{				
				request.setAttribute("messageInsertDataVocab",e.getMessage());
			}
		}

		
		// add file xlsx to folder: FileExcelVocab
		
		public static String UploadFileExcelVocab(HttpServletRequest request, HttpServletResponse response,int vocabularyguidelineid,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";

			ServletContext context = request.getServletContext();
			
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("FileExcelVocab/");
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
					    				VocabularyManageDAO.ImportExcelVocab(request, response, conn, pathFile, vocabularyguidelineid);
					    				
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
		
		public static void CheckVocabContent(HttpServletRequest request,int checkcontent,int vocabularyguidelineid,Connection conn) {
			PreparedStatement ps = null;
			String sql = "update vocabularyguideline set checkcontent=? where vocabularyguidelineid="+vocabularyguidelineid;
			
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1,checkcontent);
				ps.executeUpdate();
				
				ps.close();
				
			} catch (SQLException e) {
				
				request.setAttribute("errorCheckVocabContent", e.getMessage());
			}
			
		}
		

		// Add file Audio and file Image for Vocabulary
		
		public static String AddAudioImageVocab(HttpServletRequest request, HttpServletResponse response,Connection conn) 
				throws IOException, ServletException {
			
			String test ="";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html;charset=UTF-8");
			
			final String Address=context.getRealPath("ImageAudioVocab/");
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
