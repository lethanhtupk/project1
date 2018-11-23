package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Answeruser;
import BEAN.Examinationquestion;
import BEAN.Result;
import DAO.ExaminationManageDAO;
import DB.DBConnection;


@WebServlet("/DoingFullExamToeic2")
public class DoingFullExamToeic2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DoingFullExamToeic2() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnect();

		String examinationidstr = request.getParameter("examinationid");
		int examinationid = Integer.parseInt(examinationidstr);
		
		String memberidstr = request.getParameter("memberid");
		int memberid = Integer.parseInt(memberidstr);
		
		
		int countrow = ExaminationManageDAO.CountNumberQuestionExam(conn, examinationid);
		
		
		List<Examinationquestion> listcorrectanswer = ExaminationManageDAO.ListCorrectAnswer(conn, examinationid, request);
		
		
		List<Answeruser> listansweruser = new ArrayList<Answeruser>();
		
		
		int socaudung=0;
		int socaudungphannghe=0;
		int socaudungphandoc=0;
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
				
				String dapandung = ExaminationManageDAO.CorrectAnswerOneQuestion(conn, examinationid, i);
				
				if (i<=10)
				{
					if (answer.equals(dapandung))
					{
						socaudung++;
						socaudungphannghe++;
					}
				}
				else 
				{
					if (i>=11)
					{
						if (answer.equals(dapandung))
						{
							socaudung++;
							socaudungphandoc++;
						}
					}
				}
					

					
			}
			else
			{
				Answeruser au = new Answeruser();	
				au.setNum(i);
				au.setAnswer("Không chọn ĐA");		
				listansweruser.add(au);
			}
				
			
		}
		
		
		Date date = new Date();
		
		
		int socausai = countrow - socaudung;
		
		
		Result result = new Result();
		
		result.setCorrectanswernum(socaudung);
		result.setIncorrectanswernum(socausai);
		result.setTime(date.toString());
		result.setExaminationid(examinationid);
		result.setMemberid(memberid);
		result.setCorrectanswerlisten(socaudungphannghe);
		result.setCorrectanswerread(socaudungphandoc);
		
		ExaminationManageDAO.AddResult(conn, result);
		
		request.setAttribute("kitutrongdatabase","\n");
		request.setAttribute("kitutronghtml","<br/>");
		request.setAttribute("listcorrectanswer",listcorrectanswer);
		request.setAttribute("listansweruser",listansweruser);
		
		
		List<Result> list = ExaminationManageDAO.DisplayResultExamUser(conn,date.toString(), examinationid, memberid);
		
		request.setAttribute("ketquathi",list);
		
	
		RequestDispatcher rd = request.getRequestDispatcher("View/ResultExamUser.jsp");
		rd.forward(request,response);
		
			
	}

}
