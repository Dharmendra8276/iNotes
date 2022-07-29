package com.todo;

import jakarta.servlet.http.HttpServlet;


import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/signupServlet")
public class SignupServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		HttpSession session = request.getSession();
		
		try {
			// read student info from form data
			String name = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");		
			
			// create a new student object
			User theUser = new User(name, email, password);
			
			// add the student to the database
			boolean save = projectDbUtil.addUser(theUser);

			
			session.setAttribute("visitSignupServlet", "YES");
			if(save) {
				session.setAttribute("signup-success", "YES");
				session.setMaxInactiveInterval(30);
				response.sendRedirect("login.jsp");
			}
			else {
				response.sendRedirect("signup.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
