package com.ebook.model.user;

import com.ebook.dal.UserDAO;

public class UserManager {
	private UserDAO userDAO = new UserDAO();
	
	//get user by user name
	public User GetUser(String username) {
		
		User user = null;
			
		try {
			user = userDAO.getUser(username);
	    	return user;
	    } catch (Exception se) {
	      System.err.println("UserService: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return user;
	}
	
	//register new user in the DB
	public void addUser(User user) {
		
		try {
			userDAO.createUser(user);
	    } catch (Exception se) {
	      System.err.println("UserService: Threw a Exception creating user.");
	      System.err.println(se.getMessage());
	    }
	}
	
	
	//Delete customer from DB
	public void deleteUser(User user) {	
		try {
			userDAO.removeUser(user);
	    } catch (Exception se) {
	      System.err.println("UserService: Threw a Exception removing user.");
	      System.err.println(se.getMessage());
	    }
	}
	//Update user in DB
	public void updateCustomer(User user) {	
		try {
			userDAO.updateUser(user);
	    } catch (Exception se) {
	      System.err.println("UserService: Threw a Exception updating user.");
	      System.err.println(se.getMessage());
	    }
	}
	
}
