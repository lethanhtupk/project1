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

@WebServlet("/UploadVocabularyImage")
public class UploadVocabularyImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UploadVocabularyImage() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		
		String test = VocabularyManageDAO.UploadImageVocabulary(request, response, vocabularyguidelineid, conn);
		
	if( test.equals("Success")) {
			
			
			List<Vocabularyguideline> vocab = VocabularyManageDAO.DisplayListVocabulary(conn, request);
			
			request.setAttribute("listvocabulary",vocab);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListVocabularyManage.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msVocabUploadImage", test);
			request.setAttribute("vocabularyguidelineid",vocabularyguidelineid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageVobabulary.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}

}
