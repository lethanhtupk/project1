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


@WebServlet("/ListExerciseListeningController")
public class ListExerciseListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ListExerciseListeningController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		List<Listenexercise> listle = ExerciseListeningDAO.DisplayListExerciseListening(conn, request);
				
		
		request.setAttribute("listexerciselistening",listle);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListExerciseListening.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
