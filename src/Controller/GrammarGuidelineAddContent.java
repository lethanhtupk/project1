package Controller;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.GrammarGuideline;
import DAO.GrammarGuidelineManage;
import DB.DBConnection;


@WebServlet("/GrammarGuidelineAddContent")
public class GrammarGuidelineAddContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GrammarGuidelineAddContent() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getCharacterEncoding()==null) {
			
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreateConnect();
		
		String content = request.getParameter("content");
		
		GrammarGuideline grammarguideline = new GrammarGuideline();
		grammarguideline.setContent(content); 
		
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		
		
		
		
		boolean kt = GrammarGuidelineManage.AddContentGrammarGuideline(request, conn, grammarguideline,grammarguidelineid);
				
		if(kt) {
			
			
//			RequestDispatcher rd = request.getRequestDispatcher("ListGrammarGMforward");
//			rd.forward(request, response);
			
			List<GrammarGuideline> ggl = GrammarGuidelineManage.DisplayListGrammarGuideline(conn,request);
			
			request.setAttribute("listgrammarguideline",ggl);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineManage.jsp");
			rd.forward(request, response);
			
			
			
		}
		else {
			
			
			request.setAttribute("grammarguidelineid",grammarguidelineid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentGrammarGuideline.jsp");
			rd.forward(request, response);
		}
		
	}

}
