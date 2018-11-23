package Controller;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.GrammarGuideline;
import DAO.GrammarGuidelineManage;
import DB.DBConnection;


@WebServlet("/UploadGGlineImage")
public class UploadGGlineImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UploadGGlineImage() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DBConnection.CreateConnect();
		 String grammarguidelineidstr = request.getParameter("grammarguidelineid");
				
			
			int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
			
			GrammarGuidelineManage.UploadFileImageGGline(request, response, grammarguidelineid);
			
//			RequestDispatcher rd = request.getRequestDispatcher("ListGrammarGMforward");
//			rd.forward(request, response);
			
			

//			String test=GrammarGuidelineManage.UploadFileImageGGline(request, response, grammarguidelineid);
			
			//					if(test.equals("success")) {
			//						
			//						
			//						//RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineManage.jsp");
			//		//				RequestDispatcher rd = request.getRequestDispatcher("ListGrammarGMforward");
			//		//				rd.forward(request, response);
			//						
			//						
			//						List<GrammarGuideline> ggl = GrammarGuidelineManage.DisplayListGrammarGuideline(conn,request);
			//						
			//						request.setAttribute("listgrammarguideline",ggl);
			//						
			//						RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ListGrammarGuidelineManage.jsp");
			//						rd.forward(request, response);
			//						
			//									}
			//			
			//					else {
			//						
			//						request.setAttribute("msgrammarguidelineimage", test);
			//						
			//						RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
			//						rd.forward(request, response);
			//						
			//						
			//						}
		
		
//			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertImageGGuideline.jsp");
//			rd.forward(request, response);
		
		
		
//		GrammarGuidelineManage.UploadFileImageGGline(request, response,grammarguidelineid);
		
		
		
		
		
		
	}

}
