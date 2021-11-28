package com.ebook.model.login;

public class User {
	
	private String userId;
	private String username;
	private String password;
	private boolean loggedInState = false;
	
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void  setPassword(String password) {
		this.password = password;
	}
	
	public boolean getloggedInState() {
		return this.loggedInState;
	}
	
	public void loginUser(String username, String password) {
		if (loggedInState == false && this.username.equals(password) && this.password.equals(password)) {
			loggedInState = true;
		} else {
			throw new IllegalStateException("Cannot login user.");
		}
	}
	
	public void logoutUser() {
		if (loggedInState = true) {
			loggedInState = false;
		} else {
			throw new IllegalStateException("Cannot logout user.");
		}
	}
	


}
