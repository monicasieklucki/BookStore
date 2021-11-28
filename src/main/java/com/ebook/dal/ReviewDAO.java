package com.ebook.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import com.ebook.model.review.Review;
import com.ebook.model.item.Product;

public class ReviewDAO {
	
	public void addReview(Review review, String customerId, Product product) {
	 	 
	    try { 		
	    	//Get review
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String insertReviewQuery = "INSERT INTO Review (review) VALUES (" + review.getReview() + ")";
	    	
	    	st.executeUpdate(insertReviewQuery);      
	    	System.out.println("ReviewDAO: *************** Query " + insertReviewQuery);
	    	
		    st.close();  
		   	      
	    }	    
	    catch (SQLException se) {
	        System.err.println("ReviewDAO: Threw a SQLException inserting the review object.");
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }
	
	public List<Review> getReviewsByCustomer(String customerId) {
		
		try {
			
			Review review = new Review();
			List<Review> reviews = new ArrayList<Review>();
			
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectReviewsByCustomerQuery = "SELECT review FROM Review WHERE customerId = '" + customerId + "'";

	    	ResultSet prodReviewsRS = st.executeQuery(selectReviewsByCustomerQuery);      
	    	System.out.println("UserDAO: *************** Query " + selectReviewsByCustomerQuery);

	    	while(prodReviewsRS.next()) {
	    		review.setReview(prodReviewsRS.getString(1));
	    		reviews.add(review);
	    	}
	    	
	    	return reviews;
		}
		catch (SQLException se) {
		    System.err.println("ReviewDAO: Threw a SQLException retrieving the review object.");
		    System.err.println(se.getMessage());
		    se.printStackTrace();			
		}
		
		return null;
		
	}
	
	public List<Review> getReviewsByProduct(String productId) {
		
		try {
			
			Review review = new Review();
			List<Review> reviews = new ArrayList<Review>();

	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectReviewsByProductQuery = "SELECT review FROM Review WHERE productId = '" + productId + "'";

	    	ResultSet prodReviewsRS = st.executeQuery(selectReviewsByProductQuery);      
	    	System.out.println("ReviewDAO: *************** Query " + selectReviewsByProductQuery);
			
	    	while(prodReviewsRS.next()) {
	    		review.setReview(prodReviewsRS.getString(1));
	    		reviews.add(review);
	    	}
	    	
	    	return reviews;
		}
		catch (SQLException se) {
		    System.err.println("ReviewDAO: Threw a SQLException retrieving the review object.");
		    System.err.println(se.getMessage());
		    se.printStackTrace();			
		}
		
		return null;
		
	}

}
