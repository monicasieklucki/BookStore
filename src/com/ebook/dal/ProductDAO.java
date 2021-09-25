package com.ebook.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.customer.Address;
import com.ebook.model.customer.Customer;
import com.ebook.model.item.Product;

public class ProductDAO {
	public ProductDAO() {}
	
	public Product getProduct(String productId) {
	 	 
	    try { 		
	    	//Get Product
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectProductQuery = "SELECT productID, title, price FROM Product WHERE productID = '" + productId + "'";

	    	ResultSet prodRS = st.executeQuery(selectProductQuery);      
	    	System.out.println("ProductDAO: *************** Query " + selectProductQuery);
	    	
	      //Get Product
    	  Product product = new Product();
	      while (prodRS.next() ) {
	    	  product.setId(prodRS.getString("productID"));
	    	  product.setTitle(prodRS.getString("title"));
	    	  product.setPrice(prodRS.getDouble("price"));
	      }
	      //close to manage resources
	      prodRS.close();
	      	   
	      //Get Book here
	      /*
	      //Get Address
	      String selectAddressQuery = "SELECT addressID, street, unit, city, state, zip FROM Address WHERE customerID = '" + customerId + "'";
	      ResultSet addRS = st.executeQuery(selectAddressQuery);
    	  Address address = new Address();
    	  
    	  System.out.println("CustomerDAO: *************** Query " + selectAddressQuery);
    	  
	      while ( addRS.next() ) {
	    	  address.setAddressId(addRS.getString("addressid"));
	    	  address.setStreet(addRS.getString("street"));
	    	  address.setUnit(addRS.getString("unit"));
	    	  address.setCity(addRS.getString("city"));
	    	  address.setState(addRS.getString("state"));
	    	  address.setZip(addRS.getString("zip"));
	      }
	      
	      customer.setBillingAddress(address);
	      //close to manage resources
	      addRS.close();
	      */
	      st.close();
	      
	      return product;
	    }	    
	    catch (SQLException se) {
	      System.err.println("ProductDAO: Threw a SQLException retrieving the product object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }

}
