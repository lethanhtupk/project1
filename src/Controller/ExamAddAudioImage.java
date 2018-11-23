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
import DAO.ExaminationManageDAO;
import DB.DBConnection;

@WebServlet("/ExamAddAudioImage")
public class ExamAddAudioImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExamAddAudioImage() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddAudioImageExam.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		
		String test = ExaminationManageDAO.AddAudioImageExam(request, response, conn);
		
		if( test.equals("Success")) {
			
			List<Examination> exam = ExaminationManageDAO.DisplayListExamination(conn, request);
			request.setAttribute("listexamination",exam);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ExaminationManage.jsp");
			rd.forward(request, response);
		}
		else {
			
			request.setAttribute("msAddAudioImageExam", test);

			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/AddContentExamination.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}
