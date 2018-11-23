package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

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

@WebServlet("/ExaminationAddContent")
public class ExaminationAddContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ExaminationAddContent() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String examinationid = request.getParameter("examinationid");
		request.setAttribute("examinationid",examinationid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentExamination.jsp");
		rd.forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Connection conn = DBConnection.CreateConnect();
		
		String examinationidstr = request.getParameter("examinationid");
		
		int examinationid = Integer.parseInt(examinationidstr);
		
		String test = ExaminationManageDAO.UploadFileExcel(request, response, examinationid, conn);
		
		if( test.equals("Success")) {
			
			// sau khi them cau hoi vao de thi, thay checkexam = 1
			ExaminationManageDAO.CheckExam(request,1, examinationid, conn);
			
			List<Examination> exam = ExaminationManageDAO.DisplayListExamination(conn, request);
			request.setAttribute("listexamination",exam);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ExaminationManage.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msExaminationUpfileExcel", test);
			request.setAttribute("examinationid",examinationid);
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentExamination.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		

		
	}
		
		
		
	}


