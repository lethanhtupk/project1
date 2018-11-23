package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Examination;
import BEAN.GrammarGuideline;
import DAO.ExaminationManageDAO;
import DAO.GrammarGuidelineManage;
import DB.DBConnection;


@WebServlet("/InsertExaminationName")
public class InsertExaminationName extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public InsertExaminationName() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DBConnection.CreateConnect();
		
		String examinationname = request.getParameter("examinationname");
		
		Examination examination = new Examination();
		examination.setExaminationname(examinationname);
		
		boolean kt = ExaminationManageDAO.InsertExaminationName(request, conn, examination);
		
		if(kt) {
			
			int examinationid = ExaminationManageDAO.RetrieveIdExamination(conn, examination, request);
			
			ExaminationManageDAO.CheckExam(request,0, examinationid, conn);
			
			request.setAttribute("examinationid",examinationid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageExamination.jsp");
			rd.forward(request, response);
			
		}
		
		else {
//			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineMannage.jsp");
//			rd.forward(request, response);
			int examinationid = ExaminationManageDAO.RetrieveIdExamination(conn, examination, request);
			request.setAttribute("examinationid",examinationid);
			
		}
		
		
	}
		
		
	}


