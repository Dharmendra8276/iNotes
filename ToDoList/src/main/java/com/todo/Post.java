package com.todo;

import java.util.Date;

public class Post {
	private int id;
	private String title;
	private String description;
	private Date noteDate;
	private User user;

	public Post(int id, String title, String description, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
	}

	public Post(int id, String title, String description, Date noteDate, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.noteDate = noteDate;
		this.user = user;
	}

	public Post(int id, String title, String description, Date noteDate) {

		this.id = id;
		this.title = title;
		this.description = description;
		this.noteDate = noteDate;
	}

	public Post(int id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public Post() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
