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

import BEAN.Vocabularyguideline;
import DAO.VocabularyManageDAO;
import DB.DBConnection;

@WebServlet("/VocabularyAddAudio")
public class VocabularyAddAudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public VocabularyAddAudio() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddAudioImageVocabulary.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		String test = VocabularyManageDAO.AddAudioImageVocab(request, response, conn);
		
		
		
		if( test.equals("Success")) {
			
			List<Vocabularyguideline> exam = VocabularyManageDAO.DisplayListVocabulary(conn, request);
					
			request.setAttribute("listvocabulary",exam);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListExerciseListening.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msAddAudioImageVocabulary", test);

			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddAudioImageVocabulary.jsp");
			rd.forward(request, response);
		}
		
	}

}
