package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import BEAN.Vocabularyguideline;
import DAO.VocabularyManageDAO;
import DB.DBConnection;

@WebServlet("/InsertVocabularyName")
public class InsertVocabularyName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertVocabularyName() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				if(request.getCharacterEncoding()==null) {
					request.setCharacterEncoding("UTF-8");
				}
				
				Connection conn = DBConnection.CreateConnect();
				
				String vocabularyname = request.getParameter("vocabularyname");
				
				Vocabularyguideline vocab = new Vocabularyguideline();
				vocab.setVocabularyname(vocabularyname);
				
				boolean kt = VocabularyManageDAO.InsertVocabularyName(request, conn, vocab);
				
				
				if(kt) {
					
					int vocabularyguidelineid = VocabularyManageDAO.RetrieveIdVocabulary(conn, vocab, request);
					
					
					
					VocabularyManageDAO.CheckVocabContent(request, 0, vocabularyguidelineid, conn);
					
					
					request.setAttribute("vocabularyguidelineid",vocabularyguidelineid);
					
					RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageVocabulary.jsp");
					rd.forward(request, response);
					
				}
				
				else {

					int vocabularyguidelineid = VocabularyManageDAO.RetrieveIdVocabulary(conn, vocab, request);
					request.setAttribute("vocabularyguidelineid",vocabularyguidelineid);
					
				}
				
				
			}
		
		
	}


