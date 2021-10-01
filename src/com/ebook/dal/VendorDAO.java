package com.ebook.dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.model.item.Product;


public class VendorDAO {
	
	public VendorDAO() {}
	
	public Vendor getVendor(Integer vendorid) {
	 	 
	    try { 		
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectVendorQuery = "SELECT vendorid, vendorname FROM vendor WHERE vendorid = " + vendorid + ";";

	    	ResultSet vendorRS = st.executeQuery(selectVendorQuery);      
	    	System.out.println("vendorDAO: *************** Query " + selectVendorQuery);
	    	
	      //Get vendor
    	  Vendor vendor = new Vendor();
	      while ( vendorRS.next() ) {
	    	  vendor.setVendorId(vendorRS.getInt("vendorid"));
	    	  vendor.setVendorName(vendorRS.getString("vendorname"));
	      }
	      //close to manage resources
	      vendorRS.close();
	      	    		  
//	      //Get vendor details
//	      String selectVendorDetailQuery = "SELECT vendorid, productid, quantity, FROM vendorline WHERE vendorid = '" + vendorid + "'";
//	      ResultSet pdRS = st.executeQuery(selectVendorDetailQuery);
//	      
//	      List<VendorLine> vendorLines = new ArrayList<VendorLine>();
//	      
//    	  System.out.println("vendorDetailDAO: *************** Query " + selectVendorDetailQuery);
//    	  
//	      while ( pdRS.next() ) {
//		      VendorLine vendorLine = new VendorLine();
//		      Product product = new Product();
//		      
//	    	  vendorLine.setQuantity(pdRS.getInt("quantity"));
//	    	  
//	    	  product.setId(pdRS.getInt("id"));
//	    	  product.setTitle(pdRS.getString("title"));
//	    	  product.setPrice(pdRS.getDouble("price"));
//	    	  
//	    	  vendorLine.setProduct(product);
//	    	  
//	    	  vendorLines.add(vendorLine);
//	      }
//	      
//	      vendor.setVendorLines(vendorLines);
//	      //close to manage resources
//	      pdRS.close();
//	      st.close();
	      
	      return vendor;
	    }	    
	    catch (SQLException se) {
	      System.err.println("vendorDAO: Threw a SQLException retrieving the vendor object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }

	public void addVendor(Vendor vendor) throws SQLException {
		String insertStm = "INSERT INTO Vendor (vendorname) VALUES (?)";
	    try ( 		
	    	
	    	Connection con = DBHelper.getConnection();
	    	PreparedStatement statement = con.prepareStatement(insertStm , Statement.RETURN_GENERATED_KEYS);
	    ){
	    	statement.setString(1, vendor.getVendorName());
	    	int affectedRows = statement.executeUpdate();
	    	if(affectedRows == 0) {
	    		throw new SQLException("Creating vendor failed, no rows affected.");
	    	}
	    	
	    	try (ResultSet generatedId = statement.getGeneratedKeys()) {
	    		if (generatedId.next()) {
	    			vendor.setVendorId(generatedId.getInt(1));
	    		} else {
	    			throw new SQLException("Creating vendor failed, no ID obtained.");
	    		}
	    	}
		      
		   	      
	    }	    
	    catch (SQLException se) {
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }
	
	public void removeVendor(Integer vendorid) {
		try {
			
	    	Statement st = DBHelper.getConnection().createStatement();

	    	//Delete Vendor Line
	    	String deleteVendorLineQuery = "DELETE FROM vendorline WHERE vendorid = " + vendorid + ";";

	    	st.executeUpdate(deleteVendorLineQuery);
	    	System.out.println("VendorDAO: *************** Query " + deleteVendorLineQuery);
	    	
	    	
	    	//Delete Vendor
	    	String deleteVendorQuery = "DELETE FROM vendor WHERE vendorid = " + vendorid + ";";

	    	st.executeUpdate(deleteVendorQuery);
	    	System.out.println("VendorDAO: *************** Query " + deleteVendorQuery);
	    	

	    	st.close();

		}
		catch (SQLException ex) {
			System.err.println("VendorDAO: Threw a SQLException deleting the vendor object.");
    	    System.err.println(ex.getMessage());
		}
	}
	
	public void addVendorProduct(Vendor vendor) {
	 	 
	    try { 		
	    	Statement st = DBHelper.getConnection().createStatement();
	    	
	    	String insertVendorDetailsQuery = "INSERT INTO vendorline (vendorid, productId, quantity) VALUES ('" + vendor.getVendorId()+ "', '" + vendor.getVendorLine().getProduct().getId() + "', '" + vendor.getVendorLine().getQuantity() + "');";
	    	
	    	st.executeUpdate(insertVendorDetailsQuery);    	
	    	
	        //close to manage resources
	        st.close();
	      
	    }	    
	    catch (SQLException se) {
	        System.err.println("VendorLineDAO: Threw a SQLException inserting the vendorline object.");
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	  }
	
	public List<Vendor> getAllVendorProducts() {
	 	 
	    try { 		
	    	
	        List<Vendor> vendorProducts = new ArrayList<Vendor>();
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectVendorQuery = "SELECT vendorid, vendorname FROM Vendor";

	    	ResultSet vendorRS = st.executeQuery(selectVendorQuery);      
	    	System.out.println("vendorDAO: *************** Query " + selectVendorQuery);
	    	
	      //Get vendor
	      List<Vendor> vendors = new ArrayList<Vendor>();
    	  Vendor vendor = new Vendor();
	      while ( vendorRS.next() ) {
	    	  vendor.setVendorId(vendorRS.getInt("vendorid"));
	    	  vendor.setVendorName(vendorRS.getString("vendorname"));
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
		    	  
		    	  product.setId(pdRS.getInt("productId"));
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
