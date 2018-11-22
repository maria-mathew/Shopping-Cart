package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer uid = (Integer)request.getSession().getAttribute("uid");
		
		if(uid == null) {
			response.sendRedirect("Login?msg=Please Login");
		}
		else {
			request.setAttribute("uid", uid);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/changePassword.jsp");
			rd.forward(request, response);	
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int uid = Integer.parseInt(request.getParameter("uid"));
		String currentPassword = request.getParameter("currentPassword");
		String newPassword1 = request.getParameter("newPass1");
		String newPassword2 = request.getParameter("newPass2");
		
		if(currentPassword.trim().equals("")|| newPassword1.trim().equals("")||newPassword2.trim().equals("")) {
			response.sendRedirect("ChangePassword?msg=Cannot leave field empty");
		}
		
		else {
		DB_Access db = new DB_Access();
		int result = db.updatePassword(currentPassword, newPassword1, newPassword2, uid);
		
		if(result==0) {
			//success
			response.sendRedirect("Home");
		}
		else if(result==1) {
			response.sendRedirect("ChangePassword?msg=password entered is not correct");
		}
		else if(result==2) {
			response.sendRedirect("ChangePassword?msg=Password do not match!");
		}}
		
		
	}

}
