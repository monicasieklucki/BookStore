package com.ebook.model.service;

import com.ebook.dal.VendorDAO;
import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;


public class VendorService {
	private static VendorDAO vendDAO = new VendorDAO();
	
	//Insert a new vendor in the DB
	public static Vendor addVendor(String vendorName) {
		Vendor vendor = new Vendor(vendorName);
		
		try {
			vendDAO.addVendor(vendor);
	    } catch (Exception se) {
	      System.err.println("VendorService: Threw a Exception adding Vendor.");
	      System.err.println(se.getMessage());
	    }
		return vendor;
	}
	
	//Delete vendor and vendor lines from DB
	public static void removeVendorById(Integer vendorId) {
		
		try {
			vendDAO.removeVendor(vendorId);
	    } catch (Exception se) {
	      System.err.println("VendorService: Threw a Exception removing vendor.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public static Vendor getVendorById(Integer vendorId) {
		Vendor vendor = null;
		try {
			vendor = vendDAO.getVendor(vendorId);
		} catch (Exception se) {
			System.err.println("VendorService: Could not find vendor id " + vendorId);
		}
		return vendor;
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
