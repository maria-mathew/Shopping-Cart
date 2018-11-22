package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChangeLoginname")
public class ChangeLoginname extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		
		if(uid == null) {
			response.sendRedirect("Login?msg=Please Login");
		}
		else {
			request.setAttribute("uid",uid);
			
			DB_Access db = new DB_Access();
			String loginname = db.getLoginName(uid);
			request.setAttribute("loginname", loginname);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/changeLoginname.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		String loginname = request.getParameter("loginname");
		
		if(loginname.trim().equals("")) {
			response.sendRedirect("ChangeLoginname?msg=Cannot leave field empty");
		}
		else {
			DB_Access db = new DB_Access();
			int result = db.changeLoginname(uid, loginname);
			
			if(result ==1) {
				response.sendRedirect("ChangeLoginname?msg=Login name exists!");
			}
			else if(result==0) {
				response.sendRedirect("Home");
			}
		}
		
		
	}

}
