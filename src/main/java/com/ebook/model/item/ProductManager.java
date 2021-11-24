package com.ebook.model.item;

import java.util.ArrayList;
import java.util.List;

import com.ebook.dal.ProductDAO;

public class ProductManager {
	
	private static ProductDAO prodDAO = new ProductDAO();
	
	public List<Product> getProducts(){
		
    	List<Product> products = new ArrayList<Product>(); 
		try {
			products = prodDAO.getProducts();
	    	return products;
	    } catch (Exception se) {
	      System.err.println("ProductService: Threw a Exception retrieving all products.");
	      System.err.println(se.getMessage());
	    }		
		return products;
	}
	
	public Product getProductById(Integer productId) {
		//search customer by ID from the DB
			
		Product product = null;
				
		try {
			product = prodDAO.getProduct(productId);
		    return product;
		} catch (Exception se) {
		    System.err.println("ProductService: Threw a Exception retrieving product.");
		    System.err.println(se.getMessage());
		}
		return product;
		
	}
}
