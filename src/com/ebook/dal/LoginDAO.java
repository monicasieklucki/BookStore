package com.ebook.dal;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.customer.Customer;
import com.ebook.model.login.User;

public class LoginDAO {
	
	public User getUser(String username) {
		
		try {

	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectUserQuery = "SELECT userID, username, password FROM User WHERE username = '" + username + "'";

	    	ResultSet userRS = st.executeQuery(selectUserQuery);      
	    	System.out.println("UserDAO: *************** Query " + selectUserQuery);
	    	
	        //Get User
			User user = new User();
	        while ( userRS.next() ) {
	    	    user.setUserName(userRS.getString("username"));
	    	    user.setPassword(userRS.getString("password"));
	        }
	      //close to manage resources
	        userRS.close();
			
			return user;	
		} 
		catch (SQLException se) {
		    System.err.println("LoginDAO: Threw a SQLException retrieving the user object.");
		    System.err.println(se.getMessage());
		    se.printStackTrace();
		}
		return null;
	}
	
	public void createUser(User user) {
		Connection con = DBHelper.getConnection();
        PreparedStatement userPst = null;
        
		try {
        	//Insert the user object
            String userStm = "INSERT INTO User (username, password) VALUES(?, ?) WHERE NOT EXISTS (SELECT * FROM User WHERE username=" + user.getUserName() + ");";
            userPst = con.prepareStatement(userStm);
            userPst.setString(1, user.getUserName());
            userPst.setString(2, user.getPassword());       
            userPst.executeUpdate();
			
		}
		catch (SQLException se) {
		    System.err.println("LoginDAO: Threw a SQLException creating the user object.");
		    System.err.println(se.getMessage());
		    se.printStackTrace();
			
		}
	}

	
}
