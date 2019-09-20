package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import di.M_Action;

@WebServlet("/center/notice/*")
public class F_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			int page = 1;
			if(request.getParameter("page")!=null)
				page = Integer.parseInt(request.getParameter("page"));
			
			request.setAttribute("page", page);
			
			String service =request.getRequestURI().substring( 
					(request.getContextPath()+"/center/notice/").length());
			
			//response.getWriter().append("f_contoller>>> "+service);
			
			request.setAttribute("mainUrl", service);
			
			
			ArrayList<String> nonCla = new ArrayList<String>();
			nonCla.add("InsertForm");
			nonCla.add("DeleteForm");
			
			if(!nonCla.contains(service)) {
				M_Action action = (M_Action)Class.forName("service."+service).newInstance();
				action.execute(request, response);
			}
			
			if(!service.equals("FileDown")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("../../view/template.jsp");
				dispatcher.forward(request, response);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
