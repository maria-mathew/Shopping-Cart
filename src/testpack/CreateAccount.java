package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/createAccount.jsp");
		rd.forward(request, response);			
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginName = request.getParameter("username");
		String userName = request.getParameter("name");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		User u = new User(1, loginName, userName, password1, password2);
		
		DB_Access db = new DB_Access();
		int result = db.createUserAccount(u);
		
		if(result==0) {
			//success
			response.sendRedirect("Login");
		}
		else if(result==1) {
			//values too long
			response.sendRedirect("CreateAccount?msg=Values too long!");
		}
		
		else if(result ==3) {
			//field empty
			response.sendRedirect("CreateAccount?msg=Cannot leave field empty");
		}
		else if(result==4) {
			//passwords does not match
			response.sendRedirect("CreateAccount?msg=Passwords does not match");
		}
		else if(result==5) {
			response.sendRedirect("CreateAccount?msg=Login Name exists");
		}
		
	}

}
