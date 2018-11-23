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
import BEAN.Readexercise;
import DAO.ExerciseListeningDAO;
import DAO.ExerciseReadingDAO;
import DB.DBConnection;


@WebServlet("/ExListeningAddContent")
public class ExListeningAddContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ExListeningAddContent() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String listenexerciseidstr = request.getParameter("listenexerciseid");
		int listenexerciseid= Integer.parseInt(listenexerciseidstr);
		request.setAttribute("listenexerciseid", listenexerciseid);
		
		RequestDispatcher  rd = request.getRequestDispatcher("View/Admin/AddContentExListening.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		String listenexerciseidstr = request.getParameter("listenexerciseid");
		
		int listenexerciseid = Integer.parseInt(listenexerciseidstr);
		
		
		String test =  ExerciseListeningDAO.UploadFileExcelExListening(request, response, listenexerciseid, conn);
		
		if( test.equals("Success")) {
			
			
			ExerciseListeningDAO.CheckExListeningContent(request, 1, listenexerciseid, conn);
		
			List<Listenexercise> list = ExerciseListeningDAO.DisplayListExerciseListening(conn, request);
			
			
			request.setAttribute("listexerciselistening",list);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListExerciseListening.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msListeningUpfileExcel", test);
			request.setAttribute("listenexerciseid",listenexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentExListening.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
		
		
	}

}
