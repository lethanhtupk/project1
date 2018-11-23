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

import BEAN.Readexercise;
import BEAN.Readquestion;
import BEAN.Vocabularycontent;
import DAO.StudyExReadingManage;
import DAO.StudyVocabularyManage;
import DB.DBConnection;

@WebServlet("/StudyExReadingController")
public class StudyExReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StudyExReadingController() {
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
		
		List<Readexercise> list = StudyExReadingManage.DisplayListExReading(pageid, count, conn);
				
		int sumrow = StudyExReadingManage.Countrow(conn);
		
		int maxpageid = (sumrow/count) + 1;
		
		
		request.setAttribute("maxpageid",maxpageid);
		request.setAttribute("listexercisereading",list);
		request.setAttribute("numberpage",Integer.parseInt(pageidstr));
		
		RequestDispatcher rd = request.getRequestDispatcher("View/ListEx-Reading.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
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
		

	
		
		
//		Connection conn = DBConnection.CreateConnect();
//		
//		String readexerciseidstr = request.getParameter("readexerciseid");
//		int readexerciseid = Integer.parseInt(readexerciseidstr);
//		
//		String pageidstr = request.getParameter("pageid");
//		int pageid = Integer.parseInt(pageidstr);
//		
//		int count = 4;
//		 // if pageid = 1 thì ko phân trang. khác 1 thì phân trang
//		
//		if(pageid == 1) {
//			
//		}
//		else {
//			pageid = pageid - 1;
//			pageid = pageid * count + 1;
//		}
//		
//		
//		
//		int sumrow = StudyExReadingManage.CountQuestionExReading(conn, readexerciseid);
//		
//		int maxpageid = (sumrow/count) + 1;
//		
//		String readexercisename = StudyExReadingManage.getNameExReading(readexerciseid, conn);
//		
//		List<Readquestion> list =  StudyExReadingManage.DisplayDetailExReadingContent(conn, readexerciseid);
//		
//		request.setAttribute("maxpageid",maxpageid);
//		
//		request.setAttribute("numberpage",Integer.parseInt(pageidstr));	
//		
//		request.setAttribute("ExReadingName",readexercisename);
//		
//		request.setAttribute("listexercisereading", list);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("View/DetailStudyExReading.jsp");
//		rd.forward(request, response);
		
//		String grammarname = list.get(0).getGrammarname();
//		String content = list.get(0).getContent();
//		
//		
//		request.setAttribute("grammarname", grammarname);
//		request.setAttribute("grammaid",grammarid);
//		request.setAttribute("content", content);
		
//		request.setAttribute("kitutrongdatabase1","\n");
//		request.setAttribute("kitutronghtml1","<br/>");
	
		
	}
	
}


