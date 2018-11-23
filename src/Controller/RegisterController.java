package Controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Member;
import DAO.RegisterDAO;
import DB.DBConnection;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Register.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// kiá»ƒm tra thÃªm Ä‘c tiáº¿ng viá»‡t ???
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		
		Connection conn = DBConnection.CreateConnect();
		
		String membername = request.getParameter("membername");
		String memberpass = request.getParameter("memberpass");
		String fullname = request.getParameter("fullname");
		
		Member mb = new Member();
		
		mb.setMembername(membername);
		mb.setMemberpass(memberpass);
		mb.setFullname(fullname);
		mb.setCategorymemberid(1);
		
		
		boolean test = RegisterDAO.InsertMember(request, conn, mb);
		
		if(test==true) {
			
			request.setAttribute("msgregister", "Đăng kí tài khoản thành công");
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Register.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("msgregister", "Đăng kí tài khoản không thành công");
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Register.jsp");
			rd.forward(request, response);
		}
	}

}
