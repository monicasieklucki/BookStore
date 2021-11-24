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
	
	/**
	 * Get a product for the given ID
	 * @param productId Id of the product to retrieve
	 * @return Product for the given ID
	 */
	public Product getProduct(Integer productId) {
	 	 
	    try { 		
	    	//Get Product
	    	Connection con = DBHelper.getConnection();
	    	Statement st = con.createStatement();
	    	String selectProductQuery = "SELECT id, title, price FROM Product WHERE id = " + productId + ";";

	    	ResultSet prodRS = st.executeQuery(selectProductQuery);      
	    	System.out.println("ProductDAO: *************** Query " + selectProductQuery);
	    	
	      //Get Product
    	  Product product = new Product();
	      while (prodRS.next() ) {
	    	  product.setId(prodRS.getInt("id"));
	    	  product.setTitle(prodRS.getString("title"));
	    	  product.setPrice(prodRS.getDouble("price"));
	      }
	      //close to manage resources
	      prodRS.close();
	      	   
	      st.close();
	      con.close();
	      
	      return product;
	    }	    
	    catch (SQLException se) {
	      System.err.println("ProductDAO: Threw a SQLException retrieving the product object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	
	/**
	 * Get a list of all available products in the store
	 * @return List of all products available in the store.
	 */
	public List<Product> getProducts() {
	 	 
	    try { 		
	    	List<Product> products = new ArrayList<Product>();
	    	//Get Product
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectProductQuery = "SELECT id, title, price FROM Product";

	    	ResultSet prodRS = st.executeQuery(selectProductQuery);      
	    	System.out.println("ProductDAO: *************** Query " + selectProductQuery);
	    	
	      //Get Product
	      while (prodRS.next() ) {
	    	  Product product = new Product();

	    	  product.setId(prodRS.getInt("id"));
	    	  //String title = prodRS.getString("title");
	    	  //System.out.println(title);
	    	  //product.setTitle(title);
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
	
	/**
	 * Adds a product to the store. Does not relate the product to any vendor.
	 * @param product product to be added.
	 */
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
