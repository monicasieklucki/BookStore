package com.ebook.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import com.ebook.model.item.Product;
import com.ebook.model.item.Book;

public class ProductDAO {
	public ProductDAO() {}
	
	public Product getProduct(Integer productId) {
	 	 
	    try { 		
	    	//Get Product
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectProductQuery = "SELECT ID, title, price FROM Product WHERE ID = " + productId + ";";

	    	ResultSet prodRS = st.executeQuery(selectProductQuery);      
	    	System.out.println("ProductDAO: *************** Query " + selectProductQuery);
	    	
	      //Get Product
    	  Product product = new Product();
	      while (prodRS.next() ) {
	    	  product.setId(prodRS.getInt("ID"));
	    	  product.setTitle(prodRS.getString("title"));
	    	  product.setPrice(prodRS.getDouble("price"));
	      }
	      //close to manage resources
	      prodRS.close();
	      	   
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
	
	public List<Product> getProducts() {
	 	 
	    try { 		
	    	List<Product> products = new ArrayList<Product>();
	    	//Get Product
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectProductQuery = "SELECT productID, title, price FROM Product";

	    	ResultSet prodRS = st.executeQuery(selectProductQuery);      
	    	System.out.println("ProductDAO: *************** Query " + selectProductQuery);
	    	
	      //Get Product
    	  Product product = new Product();
	      while (prodRS.next() ) {
	    	  product.setId(prodRS.getInt("productID"));
	    	  product.setTitle(prodRS.getString("title"));
	    	  product.setPrice(prodRS.getDouble("price"));
	    	  products.add(product);
	      }
	      //close to manage resources
	      prodRS.close();
	      	   
	      st.close();
	      
	      return products;
	    }	    
	    catch (SQLException se) {
	      System.err.println("ProductDAO: Threw a SQLException retrieving the product object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	
	public void addProduct(Product product) {
	 	String insertStm =  "INSERT INTO Product (title, price) VALUES (?, ?)";
	    try (
	    	Connection con = DBHelper.getConnection();
		    PreparedStatement statement = con.prepareStatement(insertStm , Statement.RETURN_GENERATED_KEYS);
	    ){	
	    	statement.setString(1, product.getTitle());
	    	statement.setDouble(2, product.getPrice());

	    	int affectedRows = statement.executeUpdate();
	    	if(affectedRows == 0) {
	    		throw new SQLException("Creating product failed, no rows affected.");
	    	}
	    	
	    	try (ResultSet generatedId = statement.getGeneratedKeys()) {
	    		if (generatedId.next()) {
	    			product.setId(generatedId.getInt(1));
	    		} else {
	    			throw new SQLException("Creating product failed, no ID obtained.");
	    		}
	    	}	   	      
	    }	    
	    catch (SQLException se) {
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }

}
