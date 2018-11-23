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

import BEAN.CmtVocabulary;
import DAO.CommentVocabularyDAO;
import DB.DBConnection;


@WebServlet("/CommentVocabController")
public class CommentVocabController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CommentVocabController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getCharacterEncoding()!=null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DBConnection.CreateConnect();
		
		String cmtvocabularycontent = request.getParameter("cmtvocabularycontent");
		String memberidstr = request.getParameter("memberid");
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		int memberid = Integer.parseInt(memberidstr);
		
		int countComment = CommentVocabularyDAO.CountComment(conn, vocabularyguidelineid);
		
		
		CmtVocabulary cmtVocab = new CmtVocabulary();
		
		
		cmtVocab.setCmtvocabularycontent(cmtvocabularycontent);
		cmtVocab.setMemberid(memberid);
		cmtVocab.setVocabularyguidelineid(vocabularyguidelineid);
	
		CommentVocabularyDAO.InsertCmtVocab(conn, cmtVocab, request);
	 
	 
	 
	
	 
	 List<CmtVocabulary> list = CommentVocabularyDAO.DisplaylistCmtVocabulary(conn, vocabularyguidelineid);
	 
	 request.setAttribute("listcommentvocab",list);
	 request.setAttribute("countComment", countComment);
	 
	 RequestDispatcher rd = request.getRequestDispatcher("View/ListCmtVocabulary.jsp");
	 rd.forward(request, response);
		
	}

}
