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

import BEAN.Listenexercise;
import DAO.ExerciseListeningDAO;

import DB.DBConnection;

@WebServlet("/UploadExListeningImage")
public class UploadExListeningImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UploadExListeningImage() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnect();
		
		String listenexerciseidstr = request.getParameter("listenexerciseid");
		
		int listenexerciseid = Integer.parseInt(listenexerciseidstr);
		
		
		String test = ExerciseListeningDAO.UploadImageExListening(request, response, listenexerciseid, conn);
		
		if( test.equals("Success")) {
			
			List<Listenexercise> listrx = ExerciseListeningDAO.DisplayListExerciseListening(conn, request);
			
			request.setAttribute("listexerciselistening",listrx);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListExerciseListening.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msUploadImageExListening", test);
			request.setAttribute("listenexerciseid",listenexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExListening.jsp");
			rd.forward(request, response);
		}
		
	}

}
