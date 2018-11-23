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
import DAO.CommentGrammarDAO;
import DB.DBConnection;


@WebServlet("/CommentGrammarController")
public class CommentGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CommentGrammarController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getCharacterEncoding()!=null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		
		Connection conn = DBConnection.CreateConnect();
		
		String cmtgrammarcontent = request.getParameter("cmtgrammarcontent");
		String memberidstr = request.getParameter("memberid");
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		int memberid = Integer.parseInt(memberidstr);
		
		
		CmtGrammar cmt = new CmtGrammar();
		
		cmt.setCmtgrammarcontent(cmtgrammarcontent);
		cmt.setMemberid(memberid);
		cmt.setGrammarguidelineid(grammarguidelineid);
		
	
	 CommentGrammarDAO.InsertCmtGrammar(conn, cmt, request);
	 
	 
	
	 
	 List<CmtGrammar> list = CommentGrammarDAO.DisplaylistCmtGrammar(conn, grammarguidelineid,request);
	 
	 request.setAttribute("listcommentgrammar",list);
	 
	
	 
	 RequestDispatcher rd = request.getRequestDispatcher("View/ListCmtGrammar.jsp");
	 rd.forward(request, response);
	 
		
	}

}
