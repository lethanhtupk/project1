package Controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.CmtGrammar;
import BEAN.GrammarGuideline;
import DAO.CommentGrammarDAO;
import DAO.StudyGrammarManage;
import DB.DBConnection;




@WebServlet("/StudyGrammarController")
public class StudyGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StudyGrammarController() {
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
		
		List<GrammarGuideline> list = StudyGrammarManage.DisplayListGrammar(pageid, count, conn);
		
		int sumrow = StudyGrammarManage.Countrow(conn);
		
		int maxpageid = (sumrow/count) + 1;
		
		request.setAttribute("maxpageid",maxpageid);
		request.setAttribute("listggl",list);
		request.setAttribute("numberpage",Integer.parseInt(pageidstr));
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/StudyGrammar.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
//		
//		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
//		
//		Connection conn = DBConnection.CreateConnect();
//		
//		List<GrammarGuideline> list = StudyGrammarManage.DisplayDetailGrammarContent(conn, grammarguidelineid);
//		
//		int countcomment = CommentGrammarDAO.CountComment(conn, grammarguidelineid, request);
//
//		
//		String grammarname = list.get(0).getGrammarname();
//		String content = list.get(0).getContent();
//		
//		
//		request.setAttribute("grammarname", grammarname);
//		request.setAttribute("grammarid",grammarid);
//		request.setAttribute("content", content);
//		
//		request.setAttribute("countcomment", countcomment);
//		
//		request.setAttribute("kitutrongdatabase1","\n");
//		request.setAttribute("kitutronghtml1","<br/>");
//	
//		//show list comment grammar
//		
//		List<CmtGrammar> cmtcomment = CommentGrammarDAO.DisplaylistCmtGrammar(conn, grammarid);
//		
//		request.setAttribute("listcommentgrammar", cmtcomment);
//			
//		
//		
//		request.setAttribute("kitutrongdatabase2","**");
//		request.setAttribute("kitutronghtml2","<b>");
//
//		
//		RequestDispatcher rd = request.getRequestDispatcher("View/DetailStudyGrammar.jsp");
//		rd.forward(request, response);
		
		
		Connection conn = DBConnection.CreateConnect();
		
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		

		
		List<GrammarGuideline> list = StudyGrammarManage.DisplayDetailGrammarContent(conn, grammarguidelineid);
		String grammarname = list.get(0).getGrammarname();
		String content = list.get(0).getContent();

		
		List<CmtGrammar> cmtcomment = CommentGrammarDAO.DisplaylistCmtGrammar(conn, grammarguidelineid, request);
		
		int countComment = CommentGrammarDAO.CountComment(conn, grammarguidelineid, request);
		

		request.setAttribute("listcommentgrammar", cmtcomment);
		

		
		request.setAttribute("listgrammarcontent", list);
		
		request.setAttribute("grammarguidelineid", grammarguidelineid);
		
		request.setAttribute("countComment", countComment);
		request.setAttribute("grammarname", grammarname);
		request.setAttribute("content", content);
		
		request.setAttribute("kitutrongdatabase1","\n");
		request.setAttribute("kitutronghtml1","<br/>");
		request.setAttribute("kitutrongdatabase2","**");
		request.setAttribute("kitutronghtml2","<b>");

		
		RequestDispatcher rd = request.getRequestDispatcher("View/DetailStudyGrammar.jsp");
		rd.forward(request, response);
		
	}

}
