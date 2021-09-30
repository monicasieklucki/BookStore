package com.ebook.model.service;

import com.ebook.dal.VendorDAO;
import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;


public class VendorService {
	private VendorDAO vendDAO = new VendorDAO();
	
	//Insert a new vendor in the DB
	public void addVendor(Vendor vendor) {
		
		try {
			vendDAO.addVendor(vendor);
	    } catch (Exception se) {
	      System.err.println("VendorService: Threw a Exception adding Vendor.");
	      System.err.println(se.getMessage());
	    }
	}
	
	
	//Delete vendor and vendor lines from DB
	public void removeVendorById(Integer vendorid) {
		
		try {
			vendDAO.removeVendor(vendorid);
	    } catch (Exception se) {
	      System.err.println("VendorService: Threw a Exception removing vendor.");
	      System.err.println(se.getMessage());
	    }
	}
	
	//Insert vendor product to DB
	public void addVendorProduct(Vendor vendor) {
		
		try {
			vendDAO.addVendorProduct(vendor);
	    } catch (Exception se) {
	      System.err.println("VendorService: Threw a Exception removing vendor.");
	      System.err.println(se.getMessage());
	    }
	}
	

}
