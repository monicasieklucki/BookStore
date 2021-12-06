package com.ebook.dal;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.user.User;

public class UserDAO {
	
	public User getUser(String username) {
		
		try {

	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectUserQuery = "SELECT id, username, email, password FROM users WHERE username = '" + username + "'";

	    	ResultSet userRS = st.executeQuery(selectUserQuery);      
	    	System.out.println("UserDAO: *************** Query " + selectUserQuery);
	    	
	        //Get User
			User user = new User();
	        while ( userRS.next() ) {
	    	    user.setId(userRS.getInt("id"));
	    	    user.setUsername(userRS.getString("username"));
	    	    user.setEmail(userRS.getString("email"));
	    	    user.setPassword(userRS.getString("password"));
	        }
	      //close to manage resources
	        userRS.close();
	    	st.close();

			return user;	
		} 
		catch (SQLException se) {
		    System.err.println("UserDAO: Threw a SQLException retrieving the user object.");
		    System.err.println(se.getMessage());
		    se.printStackTrace();
		}
		return null;
	}
	
	public void createUser(User user) {
	 	String insertStm = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

	    try (
		    	Connection con = DBHelper.getConnection();
			    PreparedStatement statement = con.prepareStatement(insertStm , Statement.RETURN_GENERATED_KEYS);
		    ){	
		    	statement.setString(1, user.getUsername());
		    	statement.setString(2, user.getEmail());
		    	statement.setString(3, user.getPassword());

		    	int affectedRows = statement.executeUpdate();
		    	if(affectedRows == 0) {
		    		throw new SQLException("Creating user failed, no rows affected.");
		    	}
		    	
		    	try (ResultSet generatedId = statement.getGeneratedKeys()) {
		    		if (generatedId.next()) {
		    			user.setId(generatedId.getInt(1));
		    		} else {
		    			throw new SQLException("Creating user failed, no ID obtained.");
		    		}
		    	}	
		    	statement.close();
		    }	    
		    catch (SQLException se) {
		        System.err.println(se.getMessage());
		        se.printStackTrace();
		    }
	}
	
	public void removeUser(User user) {
		try {
			
	    	Statement st = DBHelper.getConnection().createStatement();

	    	//Delete User
	    	String deleteUserQuery = "DELETE FROM user WHERE id = " + user.getId() + ";";
	    	st.executeUpdate(deleteUserQuery);
	    	System.out.println("UserDAO: *************** Query " + deleteUserQuery);
	   
	    	st.close();
		}
		catch (SQLException ex) {
			System.err.println("UserDAO: Threw a SQLException deleting the user object.");
    	    System.err.println(ex.getMessage());
		}
	}
	
	public void updateUser(User user) {
		try {
	    	//Update User
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String updateUserQuery = "UPDATE user SET username = '" + user.getUsername() + "',email = '" + user.getEmail() + "',password = " + user.getPassword() + " WHERE customerid = '" + user.getId() + "'";
	    	st.executeUpdate(updateUserQuery);
	    	System.out.println("UserDAO: *************** Query " + updateUserQuery);
	    	
	    	
	    	st.close();
		}
		catch (SQLException ex) {
      	    System.err.println("UserDAO: Threw a SQLException updating the user object.");
    	    System.err.println(ex.getMessage());
		}
	}
	
}
