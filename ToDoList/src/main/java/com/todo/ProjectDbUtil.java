package com.todo;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ProjectDbUtil {
	private DataSource dataSource;

	public ProjectDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean addUser(User theUser) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		boolean save = false;
		try {
			// get DataBase connection
			myConn = dataSource.getConnection();

			// create SQL for insert
			String sql = "insert into user(name, email, password) values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the parameter values for the student
			myStmt.setString(1, theUser.getName());
			myStmt.setString(2, theUser.getEmail());
			myStmt.setString(3, theUser.getPassword());

			// execute SQL insert
			myStmt.execute();

			save = true;

			return save;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public User getUser(String email, String pass) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		User theUser = null;

		try {
			// get DataBase connection
			myConn = dataSource.getConnection();

			// create SQL for get Data
			String sql = "select * from user WHERE email=? and password=?";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, email);
			myStmt.setString(2, pass);

			// execute SQL for get Data
			myRs = myStmt.executeQuery();

			if (myRs.next()) {
				theUser = new User(myRs.getInt(1), myRs.getString(2), myRs.getString(3));
			}

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}

		return theUser;
	}

	public boolean addNote(String title, String desc, int uid) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		boolean save = false;

		try {
			// get DataBase connection
			myConn = dataSource.getConnection();

			String sql = "insert into post(title, description, uid) values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, title);
			myStmt.setString(2, desc);
			myStmt.setInt(3, uid);

			myStmt.execute();

			save = true;

			return save;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public List<Post> getData(int id) throws SQLException {
		List<Post> list = new ArrayList<Post>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get DataBase connection
			myConn = dataSource.getConnection();

			String query = "select * from post where uid=? order by id DESC";

			myStmt = myConn.prepareStatement(query);
			myStmt.setInt(1, id);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				int noteId = myRs.getInt("id");
				String title = myRs.getString("title");
				String description = myRs.getString("description");
				Timestamp noteDate = myRs.getTimestamp("date");

//				data.setId(myRs.getInt("id"));
//				data.setTitle(myRs.getString("title"));
//				data.setDescription(myRs.getString("description"));
//				data.setNoteDate(myRs.getTimestamp("date"));

				Post note = new Post(noteId, title, description, noteDate);

				list.add(note);
			}

			return list;

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, PreparedStatement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void updateNote(Post updatenote) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get database connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update post set title=?, description=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setString(1, updatenote.getTitle());
			myStmt.setString(2, updatenote.getDescription());
			myStmt.setInt(3, updatenote.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Post getNote(int noteId) throws Exception {
		Post theNote = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get connection to database
			myConn = dataSource.getConnection();

			// create SQL to get selected student
			String sql = "select * from post where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setInt(1, noteId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String title = myRs.getString("title");
				String desc = myRs.getString("description");
				int id = myRs.getInt("id");

				// use the studentId during construction
				theNote = new Post(id, title, desc);
			} else {
				throw new Exception("Could not find note id: " + noteId);
			}

			return theNote;

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void deleteNote(String thenoteId) throws SQLException{
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert student id to INT
			int noteId = Integer.parseInt(thenoteId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create SQL to delete student
			String sql = "delete from post where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setInt(1, noteId);

			// execute SQL statement
			myStmt.execute();
		} finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}
}
