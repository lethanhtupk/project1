package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import java.util.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Answeruser;
import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.Result;
import DAO.DoingFullExamToeicDAO;
import DAO.ExaminationManageDAO;
import DB.DBConnection;


@WebServlet("/DoingFullExamToeic")
public class DoingFullExamToeic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DoingFullExamToeic() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//->> chuyen qua file DoingFullExamToiec2.java
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection conn = DBConnection.CreateConnect();
		
		
		
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("fullname")!=null) {
			
			

			String examinationidstr = request.getParameter("examinationid");
			int examinationid = Integer.parseInt(examinationidstr);
			
			String memberidstr = request.getParameter("memberid");
			int memberid = Integer.parseInt(memberidstr);
			
			
			
			
			request.setAttribute("kitutrongdatabase1","\n");
			request.setAttribute("kitutronghtml1","<br/>");
			
			
			
			request.setAttribute("examinationid", examinationid);
			request.setAttribute("memberid", memberid);
			
			List<Examinationquestion> list = DoingFullExamToeicDAO.DisplayDetailExamination(conn, request, examinationid);
			
			request.setAttribute("listExaminationQuestion",list);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/DoingFullExamToeic.jsp");
			rd.forward(request, response);
			
		}
		else {
			
			request.setAttribute("msgerrorDoingTest","Đăng nhập trước khi làm bài thi");
			
//			RequestDispatcher rd = request.getRequestDispatcher("DisplayListFullExamToeic?pageid=1");
//			rd.forward(request, response);
			
			
			int pageid = 1;
			
			int count = 4;
			 // if pageid = 1 thÃ¬ ko phÃ¢n trang. khÃ¡c 1 thÃ¬ phÃ¢n trang
			
			if(pageid == 1) {
				
			}
			else {
				pageid = pageid - 1;
				pageid = pageid * count + 1;
			}
			
			
			
			List<Examination> list = DoingFullExamToeicDAO.DisplayListExamination(pageid, count, conn, request);
			
			int sumrow = DoingFullExamToeicDAO.Countrow(conn);
			
			int maxpageid = (sumrow/count) + 1;
			
			request.setAttribute("maxpageid",maxpageid);
			request.setAttribute("listFullExamToeic",list);
			//request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			request.setAttribute("numberpage",pageid);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/ListExamToeic.jsp");
			rd.forward(request, response);
			
			
		}
	}

}
