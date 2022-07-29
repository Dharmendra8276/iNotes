package com.todo;

import jakarta.servlet.http.*;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

import javax.sql.DataSource;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProjectDbUtil projectDbUtil;
	
	@Resource(name="jdbc/todouser")
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our student DataBaseUtil ... and pass in the connection pool/dataSource
		try {
			projectDbUtil = new ProjectDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User theUser = projectDbUtil.getUser(email, password);
			
			
			session.setAttribute("visitLoginServlet", "YES");
			if(theUser != null) {
				session.setAttribute("userDetails", theUser);
				response.sendRedirect("home.jsp");
			}
			else {
				session.setAttribute("warning", "Invalid Email And Password");
				session.setMaxInactiveInterval(2);
				response.sendRedirect("login.jsp");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
