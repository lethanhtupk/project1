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


@WebServlet("/UploadExReadingImage")
public class UploadExReadingImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UploadExReadingImage() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		String readexerciseidstr = request.getParameter("readexerciseid");
		
		int readexerciseid = Integer.parseInt(readexerciseidstr);
		
		
		String test = ExerciseReadingDAO.UploadImageExReading(request, response, readexerciseid, conn);
		
		if( test.equals("Success")) {
			
			List<Readexercise> listrx = ExerciseReadingDAO.DisplayListExerciseReading(conn, request);
			
			request.setAttribute("listexercisereading",listrx);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListExerciseReading.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msUploadImageExReading", test);
			request.setAttribute("readexerciseid",readexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExReading.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}
		
	}


