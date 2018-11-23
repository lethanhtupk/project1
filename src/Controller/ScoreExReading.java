package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Answeruser;
import BEAN.Readquestion;
import DAO.StudyExReadingManage;
import DB.DBConnection;


@WebServlet("/ScoreExReading")
public class ScoreExReading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ScoreExReading() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		Connection conn = DBConnection.CreateConnect();
		
		String readexerciseidstr = request.getParameter("readexerciseid");
		int readexerciseid = Integer.parseInt(readexerciseidstr);

		
		String readexercisename = StudyExReadingManage.getNameExReading(readexerciseid, conn);
		
		List<Readquestion> list =  StudyExReadingManage.DisplayDetailExReadingContent(conn, readexerciseid);

		
		request.setAttribute("ExReadingName",readexercisename);
		
		request.setAttribute("listexercisereadingdetail", list);
		request.setAttribute("readexerciseid", readexerciseid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/DetailStudyExReading.jsp");
		rd.forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();

		String readexerciseidstr = request.getParameter("readexerciseid");
		int readexerciseid = Integer.parseInt(readexerciseidstr);
		
		// đếm số câu hỏi của đề thi
		int countrow = StudyExReadingManage.CountNumberQuestionExReading(conn, readexerciseid);
		
		// lưu đáp án đề thi vào 1 list, dùng khi hiển thị kết quả. ResultExamUser.jsp
		List<Readquestion> listcorrectanswer = StudyExReadingManage.ListCorrectAnswer(request, conn, readexerciseid);
		
		// lưu đáp án trả lời của user
		List<Answeruser> listansweruser = new ArrayList<Answeruser>();
		
		String exreadname = StudyExReadingManage.getNameExReading(readexerciseid, conn);
		
		for (int i =1; i<= countrow; i++)
		{
			
			
			String answer = request.getParameter("ans["+i+"]");
			if (answer != null)
			{
				//luu danh sach dap an cua user
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer(answer);		
				listansweruser.add(au);
				
				String dapandung = StudyExReadingManage.CorrectAnswerOneQuestion(conn, readexerciseid, i);

					
			}
			else
			{
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer("Không chọn");		
				listansweruser.add(au);
			}
				
			
		}
		
		

		request.setAttribute("listcorrectanswer",listcorrectanswer);
		request.setAttribute("listansweruser",listansweruser);
		request.setAttribute("ExReadingName", exreadname);
		request.setAttribute("readexerciseid", readexerciseid);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/ResultExReading.jsp");
		rd.forward(request,response);
		
		
	}

}
