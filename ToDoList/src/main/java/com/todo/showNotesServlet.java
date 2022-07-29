package com.todo;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/showNotesServlet")
public class showNotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjectDbUtil projectDbUtil;

	@Resource(name = "jdbc/todouser")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our student DataBaseUtil ... and pass in the connection
		// pool/dataSource
		try {
			projectDbUtil = new ProjectDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userDetails");
		try {
			if (user != null) {
				boolean save = false;

				List<Post> notes = projectDbUtil.getData(user.getId());

				save = true;
				session.setAttribute("visitshowNotesServlet", "YES");

				if (save) {
					// add data to the request
					request.setAttribute("USER_NOTES", notes);

					RequestDispatcher patcher = request.getRequestDispatcher("showNote.jsp");
					patcher.forward(request, response);
				}

			} else {
				RequestDispatcher patcher = request.getRequestDispatcher("welcome.jsp");
				patcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
