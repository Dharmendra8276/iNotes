package com.todo;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addNotesServlet")
public class AddNotesServlet extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			String title = request.getParameter("title");
			String description = request.getParameter("description");

			// add notes to the database
			boolean save = projectDbUtil.addNote(title, description, uid);

			session.setAttribute("visitAddNoteServlet", "YES");
			if (save) {
				session.setAttribute("noteAdd", "YES");
				response.sendRedirect("addNote.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
