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

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.Result;
import DB.DBConnection;

public class ExaminationManageDAO {
	
	public static List<Examination> DisplayListExamination(Connection conn,HttpServletRequest request){
		List<Examination> list = new ArrayList<Examination>();
		
		String sql ="select * from examination";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
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
					
			
		} catch (SQLException e) {
			
			request.setAttribute("error",e.getMessage());
		}
		
		
		return list;
	}
	
	//thÃªm tÃªn cho Ä‘á»� thi vÃ o csdl
	
	public static boolean InsertExaminationName(HttpServletRequest request, Connection conn,Examination examination) {
		
		PreparedStatement ps = null;
		
		String sql = " insert into examination(examinationname) values(?)";
		
		try {
			
			ps = conn.prepareStatement(sql);
			String examinationname = examination.getExaminationname();
			ps.setString(1,examinationname);
			
			 ps.executeUpdate();
			//if(kt!= 0)
			return true;
			
			//ps.close();
			
		} catch (SQLException e) {
			
			request.setAttribute("msInsertExamName",e.getMessage());
		}
		
		return false;
	}
	

	//get id of exam name
	
	public static int RetrieveIdExamination(Connection conn,Examination examination,HttpServletRequest request) {
		
		int examinationid = 0;
		
		PreparedStatement ps = null;
		
		String sql = "Select examinationid from examination where examinationname='"+examination.getExaminationname()+"'"; 
		
		
		try {
			
			
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				examinationid = rs.getInt("examinationid");
				
			}
			
			ps.close();
			rs.close();
			
			
		} catch (SQLException e) {
			
		request.setAttribute("msg", e.getMessage());
		}
		
		
		return examinationid;
		
	}
	

	//Add name image by Id

	public static void InsertExaminationImage(HttpServletRequest request, Connection conn,String image,int examinationid) {
		
	PreparedStatement ps = null;
		
		String sql = "update examination set examinationimage=? where examinationid="+examinationid;
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,image);
			
			ps.executeUpdate();
			ps.close();
			
			
		} catch (SQLException e) {
			
			request.setAttribute("msInsertExamination",e.getMessage());
		}	
		
	}
	
	

	

	public static void UploadFileImageExam(HttpServletRequest request, HttpServletResponse response,int examinationid) 
			throws IOException, ServletException {
		
		String test="";
		ServletContext context = request.getServletContext();
		//PrintWriter out = response.getWriter();
		Connection conn = DBConnection.CreateConnect();
		
		//set font de viet tieng viet
		response.setContentType("text/html;charset=UTF-8");
		
		final String Address=context.getRealPath("/ImageAudioExam");
		final int MaxMemorySize = 1024*1024*3;//3MB
		final int MaxRequestSize = 1024*1024*50;//50MB
		
		
		
		boolean isMultpart = ServletFileUpload.isMultipartContent(request);
		if(!isMultpart) {
			
//			out.println("not have enctypr: multipart/form-data");
			request.setAttribute("msg", "not have enctypr: multipart/form-data");
			
//			test = "not have enctypr: multipart/form-data";
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
			    			
			    			request.setAttribute("msExaminationUpfile","File exists. Please choose file again!");	
			    			request.setAttribute("examinationid",examinationid);
			    			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExamination.jsp");
			    			rd.forward(request, response);
//			    			out.println("File exitst. Require: test file again!");
			    			
			    			test="File exists. Please choose file again";
			    			
			    		}
			    		else
			    		{
			    			item.write(uploadedFile);
//			    			
//			    			request.setAttribute("grammarguidelineid",grammarguidelineid);
//			    			request.setAttribute("msgrammarGGimage","Upload file success");	
			    			
			    			ExaminationManageDAO.InsertExaminationImage(request, conn, fileName, examinationid);
			    			
//			    			List<GrammarGuideline> ggl = GrammarGuidelineManage.DisplayListGrammarGuideline(conn,request);
//			    			
//			    			request.setAttribute("listgrammarguideline",ggl);
			    			
//			    			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineManage.jsp");
//			    			rd.forward(request, response);
			    			
			    			//dÃ¹ng sendRedirect thay vÃ¬ RequestDispatcher Ä‘á»ƒ ko bá»‹ lá»—i font tiáº¿ng viá»‡t.
			    			
			    			List<Examination> exam = ExaminationManageDAO.DisplayListExamination(conn, request);
			    			request.setAttribute("listexamination",exam);
			    			
			    			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ExaminationManage.jsp");
			    			rd.forward(request, response);
			    			//response.sendRedirect("Admin/ExaminationManage.jsp");
			    			
			    			test="success";
			    			
			    			
							
//							out.println("Upload file success");
//							out.println(pathFile);
//							out.println(grammarguidelineid);
			    		}
						
						
					} 
			    	catch (Exception e) 
			    	{

			    		request.setAttribute("msExaminationUpfile",e.getMessage());
			    		
			    		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExamination.jsp");
		    			rd.forward(request, response);
			    		//out.println(e.getMessage());
			    		
			    		test=e.getMessage();
						
					}
			    	 
			    	 
			    } 
			    else 
			    {
			    	request.setAttribute("msExaminationUpfile","Upload file failed");
			    	

			    	RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExamination.jsp");
	    			rd.forward(request, response);
			    	test ="Upload file failed";
			    	
			    }
			}
			
		} 
		catch (FileUploadException e) 
		{
			
			
			request.setAttribute("msExaminationUpfile", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExamination.jsp");
			rd.forward(request, response);

			test=e.getMessage();
		}
		
//		return test;
		
	}
	
	// import content exam from file excel
	
	public static void ImportExcel(HttpServletRequest request,HttpServletResponse response, Connection conn, String address, int examinationid) throws ServletException, IOException
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
				String imagequestion = dataFormatter.formatCellValue(row.getCell(1));					
				String audiogg = dataFormatter.formatCellValue(row.getCell(2));
				String audiomp3 = dataFormatter.formatCellValue(row.getCell(3));
				String paragraph = dataFormatter.formatCellValue(row.getCell(4));
				String question = dataFormatter.formatCellValue(row.getCell(5));
				String option1 = dataFormatter.formatCellValue(row.getCell(6));
				String option2 = dataFormatter.formatCellValue(row.getCell(7));
				String option3 = dataFormatter.formatCellValue(row.getCell(8));
				String option4 = dataFormatter.formatCellValue(row.getCell(9));
				String correctanswer = dataFormatter.formatCellValue(row.getCell(10));
				
				int num;
				if(numstr.equals("")) {
					num = 0;
				}
				else num = Integer.parseInt(numstr);
				
				Examinationquestion ex = new Examinationquestion();
				
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
				
				ExaminationManageDAO.InsertData(request, ex, conn);
				
				
			}
			
			//wb.close();
			
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
	
	public static void InsertData(HttpServletRequest request,Examinationquestion exams, Connection conn)
	{
		//String sql = "insert into examinationquestion(num,imagequestion,audiogg,audiomp3,paragraph,question,option1,option2,option3,option4,correctanswer,examinationid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		String sql = "insert into examinationquestion(num,imagequestion,audiogg,audiomp3,paragraph,question,"
				+ "option1,option2,option3,option4,correctanswer,examinationid) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try 
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setInt(1,exams.getNum());
			ps.setString(2, exams.getImagequestion());
			ps.setString(3,exams.getAudiogg());
			ps.setString(4,exams.getAudiomp3());
			ps.setString(5,exams.getParagraph());
			ps.setString(6,exams.getQuestion());
			ps.setString(7,exams.getOption1());
			ps.setString(8,exams.getOption2());
			ps.setString(9,exams.getOption3());
			ps.setString(10,exams.getOption4());
			ps.setString(11,exams.getCorrectanswer());
			ps.setInt(12,exams.getExaminationid());
			
			
			 ps.executeUpdate();
			
			
			ps.close();
			
		} 
		catch (SQLException e) 
		{				
			request.setAttribute("messageInsert",e.getMessage());
		}
	}
	
// add file xlsx to folder: FileExcelExam
	
	public static String UploadFileExcel(HttpServletRequest request, HttpServletResponse response,int examinationid,Connection conn) 
			throws IOException, ServletException {
		
		String test ="";

		ServletContext context = request.getServletContext();
		
		response.setContentType("text/html;charset=UTF-8");
		
		final String Address=context.getRealPath("FileExcelExam/");
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
				    				ExaminationManageDAO.ImportExcel(request, response, conn, pathFile, examinationid);
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
	
	
	
	public static void CheckExam(HttpServletRequest request,int checkexamination,int examinationid,Connection conn) {
		PreparedStatement ps = null;
		String sql = "update examination set checkexamination=? where examinationid="+examinationid;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1,checkexamination);
			ps.executeUpdate();
			
			ps.close();
			
		} catch (SQLException e) {
			
			request.setAttribute("errrorCheckExam", e.getMessage());
		}
		
	}
	

	// Add file Audio and file Iamge for Exam
	
	public static String AddAudioImageExam(HttpServletRequest request, HttpServletResponse response,Connection conn) 
			throws IOException, ServletException {
		
		String test ="";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html;charset=UTF-8");
		
		final String Address=context.getRealPath("ImageAudioExam/");
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
	
// return a list correct answer of examination
	
public static List<Examinationquestion> ListCorrectAnswer(Connection conn, int examinationid,HttpServletRequest request){
	
	List<Examinationquestion> list = new ArrayList<Examinationquestion>();
	
	String sql = "select * from examinationquestion where examinationid="+examinationid;
	PreparedStatement ps;
	
	try {
		
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			Examinationquestion ex = new Examinationquestion();
			
			 int examinationquestionid = rs.getInt("examinationquestionid");
			 int num = rs.getInt("num");
			 String imagequestion = rs.getString("imagequestion");
			 String audiogg = rs.getString("audiogg");
			 String audiomp3 = rs.getString("audiomp3");
			 String paragraph = rs.getString("paragraph");
			 String option1 = rs.getString("option1");
			 String option2 = rs.getString("option2");
			 String option3 = rs.getString("option3");
			 String option4 = rs.getString("option4");
			 String question = rs.getString("question");
			
			 
			 String correctanswer = rs.getString("correctanswer");
			 
			 
			 
			 ex.setExaminationquestionid(examinationquestionid);
			 ex.setNum(num);
			 ex.setImagequestion(imagequestion);
			 ex.setAudiogg(audiogg);
			 ex.setAudiomp3(audiomp3);
			 ex.setParagraph(paragraph);
			 ex.setOption1(option1);
			 ex.setOption2(option2);
			 ex.setOption3(option3);
			 ex.setOption4(option4);
			 ex.setCorrectanswer(correctanswer);
			 ex.setQuestion(question);
			 
			 list.add(ex);
		}
		
		
	} catch (SQLException e) {
		request.setAttribute("error", e.getMessage());
	
}
	
	return list;
}

// Count number question of examination

public static int CountNumberQuestionExam(Connection conn, int examinationid)
{
	int count = 0;
	
	
	String sql = "select count(*) from examinationquestion where examinationid="+examinationid;
	
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

// Add result exam into result table



	
	public static void AddResult(Connection conn, Result result)
	{
		PreparedStatement ptmt = null;
		
		
		String sql = "insert into result(correctanswernum,incorrectanswernum,time,examinationid,memberid,correctanswerread,correctanswerlisten) values (?,?,?,?,?,?,?)";
		
		try 
		{
			ptmt = conn.prepareStatement(sql);
			
			int correctanswernum = result.getCorrectanswernum();
			int incorrectanswernum = result.getIncorrectanswernum();
			String time = result.getTime();
			int examinationid = result.getExaminationid();
			int memberid = result.getMemberid();
			int correctanswerread = result.getCorrectanswerread();
			int correctanswerlisten = result.getCorrectanswerlisten();
			
			
			
			ptmt.setInt(1,correctanswernum);
			ptmt.setInt(2,incorrectanswernum);
			ptmt.setString(3,time);
			ptmt.setInt(4,examinationid);
			ptmt.setInt(5,memberid);
			ptmt.setInt(6,correctanswerread);
			ptmt.setInt(7,correctanswerlisten);
			
			ptmt.executeUpdate();
			
			
			ptmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	//xuat dap an dung cua 1 cau hoi trong de thi CorrectAnswer of one question
	
			public static String CorrectAnswerOneQuestion(Connection conn,int examinationid, int num)
			{
				String CorrectAnswer="";
				
				String sql = "select correctanswer from examinationquestion where examinationid="+examinationid+" and num="+num;
				
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
			
			
			
			//xuat ket qua thi cua thanh vien DisplayResultExamUser
			public static List<Result> DisplayResultExamUser(Connection conn,String time,int examinationid,int memberid)
			{
				
				List<Result> list = new ArrayList<Result>();
				
				String sql = "select * from result where time='"+time+"' and examinationid="+examinationid+" and memberid="+memberid;
				
				try 
				{
					PreparedStatement ptmt = conn.prepareStatement(sql);
					
					ResultSet rs = ptmt.executeQuery();
					
					while(rs.next())
					{
						int correctanswernum = rs.getInt("correctanswernum");
						int incorrectanswernum = rs.getInt("incorrectanswernum");
						int correctanswerread = rs.getInt("correctanswerread");
						int correctanswerlisten = rs.getInt("correctanswerlisten");
						
						Result result = new Result();
						result.setCorrectanswernum(correctanswernum);
						result.setIncorrectanswernum(incorrectanswernum);
						result.setCorrectanswerlisten(correctanswerlisten);
						result.setCorrectanswerread(correctanswerread);
						
						
						list.add(result);
						
					}
					
						
				} 
				catch (SQLException e) 
				{
					
					e.printStackTrace();
				}
				
				return list;
			}
	
	
}

