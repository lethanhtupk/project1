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

import BEAN.Listenquestion;
import DAO.StudyExListeningManage;
import DB.DBConnection;

/**
 Xử lí sự kiện khi phần trang trong trang detail.phải dùng doGet do dùng a href. trong khi trước khi phân trang lại dùng doPost
 */
@WebServlet("/StudyExListeningController2")
public class StudyExListeningController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudyExListeningController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Xử lí sự kiện cho btn Again
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//kq: kết quả của radio button. get từ DetailStudyExListening. sử dụng Ajax
		
		String kq = request.getParameter("kq");
		
		Connection conn = DBConnection.CreateConnect();
		
		String listenexerciseidstr = request.getParameter("listenexerciseid");
		int listenexerciseid = Integer.parseInt(listenexerciseidstr);
		
		String numstr = request.getParameter("num");
		int num = Integer.parseInt(numstr);
		
		
		if (kq == "")
		{
//			request.setAttribute("msglambtphannghe","Yêu cầu trả lời hết các câu hỏi");
//			
//			
//			RequestDispatcher rd = request.getRequestDispatcher("View/Thongbaoloibtnghe.jsp");
//			rd.forward(request,response);
		}
		else
		{	
			List<Listenquestion> list =  StudyExListeningManage.ListCorrectAnswer(request, conn, listenexerciseid,num);
				
			
			request.setAttribute("listcorrectanswerExListening",list);
			request.setAttribute("kq",kq);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("View/ResultExListening.jsp");
			rd.forward(request,response);
		}
		
		
		
		
	}

}
