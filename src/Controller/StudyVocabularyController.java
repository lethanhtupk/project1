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

import BEAN.CmtGrammar;
import BEAN.CmtVocabulary;
import BEAN.Vocabularycontent;
import BEAN.Vocabularyguideline;
import DAO.CommentGrammarDAO;
import DAO.CommentVocabularyDAO;
import DAO.StudyVocabularyManage;
import DAO.VocabularyManageDAO;
import DB.DBConnection;


@WebServlet("/StudyVocabularyController")
public class StudyVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public StudyVocabularyController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageidstr =request.getParameter("pageid");
		int pageid = Integer.parseInt(pageidstr);
		
		int count = 4;
		 // if pageid = 1 thì ko phân trang. khác 1 thì phân trang
		
		if(pageid == 1) {
			
		}
		else {
			pageid = pageid - 1;
			pageid = pageid * count + 1;
		}
		
		Connection conn = DBConnection.CreateConnect();
		
		List<Vocabularyguideline> list = StudyVocabularyManage.DisplayListGrammar(pageid, count, conn);
		int sumrow = StudyVocabularyManage.Countrow(conn);
		
		int maxpageid = (sumrow/count) + 1;
		
		request.setAttribute("maxpageid",maxpageid);
		request.setAttribute("listvgl",list);
		request.setAttribute("numberpage",Integer.parseInt(pageidstr));
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/StudyVocabulary.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		String vocabularyguidelineidstr = request.getParameter("vocabularyguidelineid");
		int vocabularyguidelineid = Integer.parseInt(vocabularyguidelineidstr);
		String vocabguidelinename = StudyVocabularyManage.getNameVocabularyGuideline(vocabularyguidelineid, conn);
		
		List<Vocabularycontent> list = StudyVocabularyManage.DisplayDetailVocabularyContent(conn, vocabularyguidelineid);
		
		
		List<CmtVocabulary> cmtcomment = CommentVocabularyDAO.DisplaylistCmtVocabulary(conn, vocabularyguidelineid);
	
		int countComment = CommentVocabularyDAO.CountComment(conn, vocabularyguidelineid);
		
		request.setAttribute("listcommentvocab", cmtcomment);
		
		request.setAttribute("vocabguidelinename",vocabguidelinename);
		
		request.setAttribute("listvct", list);
		
		request.setAttribute("vocabularyguidelineid", vocabularyguidelineid);
		
		request.setAttribute("countComment", countComment);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/DetailStudyVocabulary.jsp");
		rd.forward(request, response);

		
		
	}

}
