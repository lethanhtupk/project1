package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExaminationManageDAO;
import DAO.GrammarGuidelineManage;


@WebServlet("/UploadExamImage")
public class UploadExamImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UploadExamImage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String examinationidstr = request.getParameter("examinationid");
			
			
			int examinationid = Integer.parseInt(examinationidstr);
			
			ExaminationManageDAO.UploadFileImageExam(request, response, examinationid);
		
		
	}

}
