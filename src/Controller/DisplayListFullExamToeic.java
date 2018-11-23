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

import DAO.DoingFullExamToeicDAO;

import DB.DBConnection;


@WebServlet("/DisplayListFullExamToeic")
public class DisplayListFullExamToeic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public DisplayListFullExamToeic() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String pageidstr =request.getParameter("pageid");
		int pageid = Integer.parseInt(pageidstr);
		
		int count = 4;
		
		
		if(pageid == 1) {
			
		}
		else {
			pageid = pageid - 1;
			pageid = pageid * count + 1;
		}
		
		Connection conn = DBConnection.CreateConnect();
		
		List<Examination> list = DoingFullExamToeicDAO.DisplayListExamination(pageid, count, conn, request);
		
		int sumrow = DoingFullExamToeicDAO.Countrow(conn);
		
		int maxpageid = (sumrow/count) + 1;
		
		request.setAttribute("maxpageid",maxpageid);
		request.setAttribute("listFullExamToeic",list);
		request.setAttribute("numberpage",Integer.parseInt(pageidstr));
		
		
		RequestDispatcher rd = request.getRequestDispatcher("View/ListExamToeic.jsp");
		rd.forward(request, response);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
