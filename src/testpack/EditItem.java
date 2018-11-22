package testpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditItem")
public class EditItem extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int iid = Integer.parseInt(request.getParameter("id"));
	
		DB_Access db = new DB_Access();
		Item item = db.getItem(iid);
		request.setAttribute("item", item);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/edit.jsp");
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DB_Access db = new DB_Access();
		String iname = request.getParameter("name");
		String iqty = request.getParameter("qty");
		int iid = Integer.parseInt(request.getParameter("id"));
		
		int result = db.editItem(iname, iqty, iid);
		
		if(result == 0) {
			response.sendRedirect("Home");
		}
		else if(result == 1) {
			String msg="Cannot leave field empty";
			response.sendRedirect("EditItem?msg="+msg+"&id="+iid);
		}
		else if(result == 2) {
			String msg = "Enter a valid value for Quantity";
			response.sendRedirect("EditItem?msg="+msg+"&id="+iid);
		}
	}

}
