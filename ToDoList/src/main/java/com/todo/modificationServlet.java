package com.todo;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/modificationServlet")
public class modificationServlet extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// read the "command" parameter
		String theCommand = request.getParameter("command");

		try {
			switch (theCommand) {

			case "LOAD":
				loadNote(request, response);
				break;

			case "DELETE":
				deleteNote(request, response);
				break;
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		boolean save = false;
		try {
			int nid = Integer.parseInt(req.getParameter("nid"));
			String title = req.getParameter("title");
			String description = req.getParameter("description");

			Post updatenote = new Post(nid, title, description);

			// add notes to the database
			projectDbUtil.updateNote(updatenote);

			save = true;

			session.setAttribute("visitupdateNoteServlet", "YES");
			if (save) {
				session.setAttribute("noteUpdate", "YES");
				resp.sendRedirect("home.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// read student id from form data
		String thenoteId = request.getParameter("noteId");

		// delete student from database
		projectDbUtil.deleteNote(thenoteId);
		
		response.sendRedirect("/ToDoList/showNotesServlet");
	}

	private void loadNote(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from form data
		int noteId = Integer.parseInt(request.getParameter("noteId"));

		// get student from database (Database)
		Post note = projectDbUtil.getNote(noteId);

		// place student in the request attribute
		request.setAttribute("THE_NOTE", note);

		// send to JSP page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-note-form.jsp");
		dispatcher.forward(request, response);
	}
}
