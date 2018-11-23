package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Readexercise;
import DAO.ExerciseReadingDAO;
import DB.DBConnection;


@WebServlet("/InsertExReadingName")
public class InsertExReadingName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertExReadingName() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DBConnection.CreateConnect();
		
		String readname = request.getParameter("readname");
		
		Readexercise rx = new Readexercise();
		
		rx.setReadname(readname);
		
		
		boolean kt = ExerciseReadingDAO.InsertExReadingName(request, conn, rx);
		
		
		if(kt) {
			
			int readexerciseid = ExerciseReadingDAO.RetrieveIdExReading(conn, rx, request);
					
			
			
			ExerciseReadingDAO.CheckExReadingContent(request, 0, readexerciseid, conn);
			
			
			request.setAttribute("readexerciseid",readexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExReading.jsp");
			rd.forward(request, response);
			
		}
		
		else {

			int readexerciseid =  ExerciseReadingDAO.RetrieveIdExReading(conn, rx, request);
			request.setAttribute("readexerciseid",readexerciseid);
			
		}
		
		
	}
		
	

}
