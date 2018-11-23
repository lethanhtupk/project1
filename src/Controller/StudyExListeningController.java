package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Listenexercise;
import BEAN.Listenquestion;
import DAO.StudyExListeningManage;

import DB.DBConnection;


@WebServlet("/StudyExListeningController")
public class StudyExListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StudyExListeningController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pageidstr =request.getParameter("pageid");
		int pageid = Integer.parseInt(pageidstr);
		
		int count = 4;
		 // if pageid = 1 thì ko phân trang. khác 1 thì phân trang
		
		if(pageid == 1) {
			
		}
		else {
			pageid = pageid - 1;
			pageid = pageid * count + 1;
		}
		
		Connection conn = DBConnection.CreateConnect();
		List<Listenexercise> list = StudyExListeningManage.DisplayListExListening(pageid, count, conn);
		
				
		int sumrow = StudyExListeningManage.Countrow(conn);
		
		int maxpageid = (sumrow/count) + 1;
		
		
		request.setAttribute("maxpageid",maxpageid);
		request.setAttribute("listexerciselistening",list);
		request.setAttribute("numberpage",Integer.parseInt(pageidstr));
		
		RequestDispatcher rd = request.getRequestDispatcher("View/ListEx-Listening.jsp");
		rd.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			String listenexerciseidstr = request.getParameter("listenexerciseid");
			int listenexerciseid = Integer.parseInt(listenexerciseidstr);
			
			
			String pageidstr = request.getParameter("pageid");
			int pageid = Integer.parseInt(pageidstr);
			
			
			int count = 1;// hiển thị 1 câu hỏi 1 trang
			
			
			if (pageid == 1)
			{
				
			}
			else 
			{
				pageid = pageid -1;
				pageid = pageid * count +1;
			}
			
			
			Connection conn = DBConnection.CreateConnect();
			
			//Hiển thị câu hỏi, đồng thời phân trang
			List<Listenquestion> list = StudyExListeningManage.DisplayDetailExListening(request, pageid, count, conn, listenexerciseid);
				
			//Đếm số câu hỏi theo mã của bài listening
			int sumrow = StudyExListeningManage.CountQuestionExListening(conn, listenexerciseid);
			
			
			int maxpageid= 0;
			
			if ((sumrow/count)%2==0)
			{
				maxpageid = (sumrow/count);
			}
			else
			{
				maxpageid = (sumrow/count)+1;
			}
			
			request.setAttribute("maxpageid",maxpageid);
			request.setAttribute("listenexerciseid",listenexerciseid);
			
			request.setAttribute("listquestionexerciselistening",list);
			
			request.setAttribute("numberpage",Integer.parseInt(pageidstr));
			
			conn.close();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("View/DetailStudyExListening.jsp");
		rd.forward(request,response);
	}

}
