package com.example.pojo;


public class User {
	private int  userId;
	private String  userName;
	private String UserPassword;
	
	public User(String userName, String userPassword) {
		super();
		this.userName = userName;
		UserPassword = userPassword;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	
	
	
	

}
