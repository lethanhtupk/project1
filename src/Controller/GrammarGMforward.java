package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GrammarGMforward")
public class GrammarGMforward extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public GrammarGMforward() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grammarguidelineid = request.getParameter("grammarguidelineid");
		request.setAttribute("grammarguidelineid",grammarguidelineid);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/GrammarGuidelineManage.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
