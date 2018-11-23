package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.GrammarGuideline;
import DAO.GrammarGuidelineManage;

import java.sql.*;


import DB.DBConnection;


@WebServlet("/InsertGguidelineName")
public class InsertGguidelineName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertGguidelineName() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DBConnection.CreateConnect();
		String grammarname = request.getParameter("grammarname");
		GrammarGuideline grammarguideline = new GrammarGuideline();
		grammarguideline.setGrammarname(grammarname);
		
		boolean kt = GrammarGuidelineManage.InsertGrammarguidelineName(request, conn, grammarguideline);
		
		if(kt) {
			
			int grammarguidelineid = GrammarGuidelineManage.RetrieveIdGrammarGuideline(conn, grammarguideline, request);
			
			request.setAttribute("grammarguidelineid",grammarguidelineid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
			rd.forward(request, response);
			
		}
		
		else {
//			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineMannage.jsp");
//			rd.forward(request, response);
			int grammarguidelineid = GrammarGuidelineManage.RetrieveIdGrammarGuideline(conn, grammarguideline, request);
			request.setAttribute("grammarguidelineid",grammarguidelineid);
			
		}
		
		
	}

}
