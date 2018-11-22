package testpack;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewItem")
public class ViewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{Integer id = Integer.parseInt(request.getParameter("id"));
	
					DB_Access db = new DB_Access();
					Item i = db.getItem(id);
					
					request.setAttribute("item", i);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/viewItem.jsp");
					rd.forward(request, response);	
		}
		catch (Exception e){
			// not logged in, send to Login with error message
						response.sendRedirect("Login?msg=have to login first...");		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
