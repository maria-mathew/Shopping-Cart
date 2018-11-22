package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ChangeUsername")
public class ChangeUsername extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		if(uid== null) {
			response.sendRedirect("Login?msg=have to login first...");
		}
		else {
			request.setAttribute("uid", uid);
			
			DB_Access db = new DB_Access();
			String username = db.getUserName(uid);
			request.setAttribute("username", username );
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/changeUsername.jsp");
			rd.forward(request, response);	
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		String username = request.getParameter("username");
		
		if(username.trim().equals("")) {
			response.sendRedirect("ChangeUsername?msg=Cannot leave field empty!");
		}
	
		else {
		DB_Access db = new DB_Access();
		db.changeUsername(uid, username);
		response.sendRedirect("Home");
		}
	}

}
