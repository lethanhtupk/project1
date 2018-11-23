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
import javax.servlet.http.HttpSession;

import BEAN.Slidebanner;
import DAO.HomeDAO;
import DAO.LoginDAO;
import DB.DBConnection;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public LoginController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("fullname");
		session.invalidate();
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
		rd.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//kiểm tra gõ tiếng việt
//		if(request.getCharacterEncoding()==null) {
//			request.setCharacterEncoding("UTF-8");
//		}
		
		
		Connection conn = DBConnection.CreateConnect();
		
		String membername = request.getParameter("membername");
		
		String memberpass = request.getParameter("memberpass");
		
		boolean kt = LoginDAO.checkMember(conn, request, membername, memberpass);
		
		
		if(kt==true) {
			
			
//			if(membername.equals("admin")) {
				
				if(LoginDAO.Authorizationmember(request, conn, membername, memberpass) == 2) {

					System.out.println(LoginDAO.Authorizationmember(request, conn, membername, memberpass));
				String fullname = LoginDAO.getFullname(conn, request, membername, memberpass);
				
				HttpSession session = request.getSession();
				session.setAttribute("adminname",fullname);
				
				List<Slidebanner> slide = HomeDAO.DisplaySlidebanner(conn);
				request.setAttribute("listslidebanner", slide);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/HomeAdmin.jsp");
				rd.forward(request, response);
				
			}
			
			else {
				
				String fullname = LoginDAO.getFullname(conn, request, membername, memberpass);
				int memberid = LoginDAO.GetMemberid(conn, request, membername, memberpass);
				
				
				HttpSession session = request.getSession();
				
				session.setAttribute("fullname",fullname);
				session.setAttribute("membername", membername);
				session.setAttribute("memberid", memberid);
				
				List<Slidebanner> slide = HomeDAO.DisplaySlidebanner(conn);
				request.setAttribute("listslidebanner", slide);
				
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Home.jsp");
//				RequestDispatcher rd = request.getRequestDispatcher("View/Wellcome-login.jsp");
				rd.forward(request, response);
			}
			
			
		}
		else {

			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request, response);
			
		}
	}

}
