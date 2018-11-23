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


@WebServlet("/ListExaminationController")
public class ListExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ListExaminationController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		List<Examination> exam = ExaminationManageDAO.DisplayListExamination(conn, request);
		request.setAttribute("listexamination",exam);
		
//		List<GrammarGuideline> ggl = GrammarGuidelineManage.DisplayListGrammarGuideline(conn,request);
//		
//		request.setAttribute("listgrammarguideline",ggl);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ExaminationManage.jsp");
		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
