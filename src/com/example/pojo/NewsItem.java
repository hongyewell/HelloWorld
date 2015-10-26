package com.example.pojo;


public class NewsItem {
	private int id;
	private String title;
	private String content;
	private String author;
	private String time;
	
	public NewsItem() {
	}
	
	

	public NewsItem(int id,String title, String content, String author, String time) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.time = time;
	}
	

	public NewsItem(String title, String content, String author, String time) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
		this.time = time;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

	
	

}
