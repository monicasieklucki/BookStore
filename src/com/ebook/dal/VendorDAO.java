package com.ebook.dal;


import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.model.item.Product;


public class VendorDAO {
	
	public VendorDAO() {}
	
	public Vendor getVendor(String vendorId) {
	 	 
	    try { 		
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectVendorQuery = "SELECT vendorID, vendorName FROM Vendor WHERE vendorID = '" + vendorId + "'";

	    	ResultSet vendorRS = st.executeQuery(selectVendorQuery);      
	    	System.out.println("vendorDAO: *************** Query " + selectVendorQuery);
	    	
	      //Get vendor
    	  Vendor vendor = new Vendor();
	      while ( vendorRS.next() ) {
	    	  vendor.setVendorId(vendorRS.getString("vendorID"));
	    	  vendor.setVendorName(vendorRS.getString("vendorName"));
	      }
	      //close to manage resources
	      vendorRS.close();
	      	    		  
	      //Get vendor details
	      String selectVendorDetailQuery = "SELECT vendorID, quantity, FROM VendorLine WHERE vendorID = '" + vendorId + "'";
	      ResultSet pdRS = st.executeQuery(selectVendorDetailQuery);
	      
	      List<VendorLine> vendorLines = new ArrayList<VendorLine>();
	      
    	  System.out.println("vendorDetailDAO: *************** Query " + selectVendorDetailQuery);
    	  
	      while ( pdRS.next() ) {
		      VendorLine vendorLine = new VendorLine();
		      Product product = new Product();
		      
	    	  vendorLine.setQuantity(pdRS.getInt("quantity"));
	    	  
	    	  product.setId(pdRS.getString("id"));
	    	  product.setTitle(pdRS.getString("title"));
	    	  product.setPrice(pdRS.getDouble("price"));
	    	  
	    	  vendorLine.setProduct(product);
	    	  
	    	  vendorLines.add(vendorLine);
	      }
	      
	      vendor.setVendorLines(vendorLines);
	      //close to manage resources
	      pdRS.close();
	      st.close();
	      
	      return vendor;
	    }	    
	    catch (SQLException se) {
	      System.err.println("vendorDAO: Threw a SQLException retrieving the vendor object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }

		
	public void addvendor(Vendor vendor) {
	 	 
	    try { 		
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String insertVendorQuery = "INSERT INTO Vendor (vendorName) VALUES (" + vendor.getVendorName() + ")";
	    	
	    	int rowcount = st.executeUpdate(insertVendorQuery);      
	    	System.out.println("vendorDAO: *************** Query " + insertVendorQuery);
	    	
		    st.close();  
		   	      
	    }	    
	    catch (SQLException se) {
	        System.err.println("vendorDAO: Threw a SQLException inserting the vendor object.");
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }
	
	public void addVendorProduct(Vendor vendor, Product product) {
	 	 
	    try { 		
	    	Statement st = DBHelper.getConnection().createStatement();
	   
    		// key vendorDetailId is key
	    	String insertVendorDetailsQuery = "INSERT INTO VendorLine (vendorId, productId, productTitle, productPrice, quantity) VALUES (" + vendor.getVendorId()+ ", " + product.getId() + ", " + product.getTitle() + ", " + product.getPrice() + ", " + vendor.getVendorLine().getQuantity() + ");";
	    	
	    	int rowcount = st.executeUpdate(insertVendorDetailsQuery);    	
	    	
	        //close to manage resources
	        st.close();
	      
	    }	    
	    catch (SQLException se) {
	        System.err.println("vendorDetailDAO: Threw a SQLException insterting the vendor detail object.");
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }
	
	public List<Vendor> getAllVendorProducts() {
	 	 
	    try { 		
	    	
	        List<Vendor> vendorProducts = new ArrayList<Vendor>();
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectVendorQuery = "SELECT vendorID, vendorName FROM Vendor";

	    	ResultSet vendorRS = st.executeQuery(selectVendorQuery);      
	    	System.out.println("vendorDAO: *************** Query " + selectVendorQuery);
	    	
	      //Get vendor
	      List<Vendor> vendors = new ArrayList<Vendor>();
    	  Vendor vendor = new Vendor();
	      while ( vendorRS.next() ) {
	    	  vendor.setVendorId(vendorRS.getString("vendorID"));
	    	  vendor.setVendorName(vendorRS.getString("vendorName"));
	    	  vendors.add(vendor);
	    	  
	      }
	      //close to manage resources
	      vendorRS.close();
	      	    		  
	      for (int i = 0; i < vendors.size(); i++) {
		      //Get vendor details
		      String selectVendorDetailQuery = "SELECT vendorID, productId, productTitle, productPrice, quantity, FROM VendorLine WHERE vendorID = '" + vendors.get(i).getVendorId() + "'";
		      ResultSet pdRS = st.executeQuery(selectVendorDetailQuery);
		      
		      List<VendorLine> vendorLines = new ArrayList<VendorLine>();
		      
	    	  System.out.println("vendorDetailDAO: *************** Query " + selectVendorDetailQuery);
	    	  
		      while ( pdRS.next() ) {
			      VendorLine vendorLine = new VendorLine();
			      Product product = new Product();
			      
		    	  vendorLine.setQuantity(pdRS.getInt("quantity"));
		    	  
		    	  product.setId(pdRS.getString("productId"));
		    	  product.setTitle(pdRS.getString("productTitle"));
		    	  product.setPrice(pdRS.getDouble("productPrice"));
		    	  
		    	  vendorLine.setProduct(product);
		    	  
		    	  vendorLines.add(vendorLine);
		      }
		      vendor.setVendorLines(vendorLines);
		      
		      pdRS.close();
	      }

	      //close to manage resources
	      st.close();
	      
	      return vendorProducts;
	    }	    
	    catch (SQLException se) {
	      System.err.println("vendorDAO: Threw a SQLException retrieving the vendor object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }


	
	
}
