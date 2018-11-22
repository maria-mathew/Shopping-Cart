package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer uid = (Integer) request.getSession().getAttribute("uid");
		if(uid == null) {
			// not logged in, send to Login with error message
			response.sendRedirect("Login?msg=have to login first...");
		}
		else {
			request.setAttribute("uid", uid);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/addItem.jsp");
			rd.forward(request, response);	
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int uid = Integer.parseInt(request.getParameter("uid"));
		String name = request.getParameter("name");
		String qty = request.getParameter("qty");
		
		
		DB_Access db = new DB_Access();
		int result = db.addItem(name, qty, uid);
		
		if(result ==0) {
			response.sendRedirect("Home");
		}
		else if(result ==1) {
			String msg = "Cannot leave field empty";
			response.sendRedirect("AddItem?msg="+msg);
		}
		else if(result==2) {
			String msg = "Enter a valid quantity";
			response.sendRedirect("AddItem?msg="+msg);
		}
		
	}

}
