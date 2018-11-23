package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Listenexercise;
import DAO.ExerciseListeningDAO;
import DB.DBConnection;


@WebServlet("/InsertExListeningName")
public class InsertExListeningName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertExListeningName() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DBConnection.CreateConnect();
		
		String listenexercisename = request.getParameter("listenname");
		
		Listenexercise le = new Listenexercise();
		
		le.setListenexercisename(listenexercisename);
		
		
		boolean kt = ExerciseListeningDAO.InsertExListeningName(request, conn, le);
				
		
		
		if(kt) {
			
			int listenexerciseid = ExerciseListeningDAO.RetrieveIdListening(conn, le, request);
					
			
			ExerciseListeningDAO.CheckExListeningContent(request, 0, listenexerciseid, conn);
			
			
			request.setAttribute("listenexerciseid",listenexerciseid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExListening.jsp");
			rd.forward(request, response);
			
		}
		
		else {

			int listenexerciseid =  ExerciseListeningDAO.RetrieveIdListening(conn, le, request);
			request.setAttribute("listenexerciseid",listenexerciseid);
			
		}
		
		
	}
		
	}


