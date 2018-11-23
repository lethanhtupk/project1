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

@WebServlet("/GrammarGuidelineDeleteContent")
public class GrammarGuidelineDeleteContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GrammarGuidelineDeleteContent() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection conn = DBConnection.CreateConnect();
		
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		
		
		GrammarGuidelineManage.DeleteGrammarGLinCmtGrammar(conn, grammarguidelineid,request);
		GrammarGuidelineManage.DeleteGrammarGuideline(conn, grammarguidelineid,request);
		
		
		List<GrammarGuideline> ggl = GrammarGuidelineManage.DisplayListGrammarGuideline(conn,request);
		
		request.setAttribute("listgrammarguideline",ggl);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineManage.jsp");
		rd.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
