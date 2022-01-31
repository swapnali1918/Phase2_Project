package core.programs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserLogin() {
        super();
       
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();  
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
			String id = "madhuri@gmail.com";
			String pass = "madhuri23";
			
			if(email.equals(id) && password.equals(pass))
			{
				response.getWriter().write("<br><br><center><h2>Login Successfully!!! </h2>");
				 out.print("<br><form action = 'logout.jsp'><br>");
				    out.print("<input type='submit' value='Logout'></center>");  
				    out.print("</form>");  
			
			}
			else {
			response.getWriter().write("<br><br><center><h2>Invalid Email_id and Password </h2><br>");
			response.getWriter().write("<p>If you want to login ?</p>");
			out.print("<form action = 'loginpage.html'><br>");
		    out.print("<input type='submit' value='Login'></center>");  
		    out.print("</form>");
			}
			 
			
	}

}
