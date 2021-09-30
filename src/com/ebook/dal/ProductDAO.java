package com.ebook.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import com.ebook.model.item.Product;
import com.ebook.model.item.Book;

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
	    	  product.setId(prodRS.getInt("productID"));
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
	 	 
	    try { 		
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String insertProductQuery = "INSERT INTO Product (title, price) VALUES (" + product.getTitle() + ", " + product.getPrice() + ")";
	    	
	    	st.executeUpdate(insertProductQuery);      
	    	System.out.println("productDAO: *************** Query " + insertProductQuery);
	    	
		    st.close();  
		   	      
	    }	    
	    catch (SQLException se) {
	        System.err.println("productDAO: Threw a SQLException inserting the product object.");
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }

}
