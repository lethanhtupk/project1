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

import BEAN.GrammarGuideline;
import BEAN.Vocabularyguideline;
import DAO.SearchDAO;
import DB.DBConnection;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SearchController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		// lấy giá trị của chuỗi nhập vào, từ ô tìm kiếm.home.jsp. phần ajax
		String grammarname1 = request.getParameter("search");
		
	
		String searchText = grammarname1.trim();
		
		System.out.println(searchText);
		
		List<GrammarGuideline> listgrammar = SearchDAO.DisplayResultSearchGrammar(conn, searchText, request);
		List<Vocabularyguideline> listvocab = SearchDAO.DisplayResultSearchVocab(conn, searchText, request);
		
		request.setAttribute("listsearch", listgrammar);
		
		request.setAttribute("listsearchvocab", listvocab);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/ResultSearch.jsp");
		rd.forward(request, response);
	}

}
