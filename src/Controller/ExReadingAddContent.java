package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import BEAN.Readexercise;

import DAO.ExerciseReadingDAO;
import DB.DBConnection;


@WebServlet("/ExReadingAddContent")
public class ExReadingAddContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExReadingAddContent() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String readexerciseidstr =request.getParameter("readexerciseid");
		int readexerciseid = Integer.parseInt(readexerciseidstr);
		
		request.setAttribute("readexerciseid", readexerciseid);
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentExReading.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		String readexerciseidstr = request.getParameter("readexerciseid");
		
		int readexerciseid = Integer.parseInt(readexerciseidstr);
		
		
		String test = ExerciseReadingDAO.UploadFileExcelExReading(request, response, readexerciseid, conn);
		;
		if( test.equals("Success")) {
			
			
		
			ExerciseReadingDAO.CheckExReadingContent(request,1, readexerciseid, conn);
			List<Readexercise> list = ExerciseReadingDAO.DisplayListExerciseReading(conn, request);
			
			request.setAttribute("listexercisereading",list);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListExerciseReading.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msExReadingUpfileExcel", test);
			request.setAttribute("readexerciseid",readexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentExReading.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		

		
	}
		
		
	}


