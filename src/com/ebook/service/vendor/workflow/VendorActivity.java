package com.ebook.service.vendor.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorManager;
import com.ebook.service.vendor.representation.VendorRepresentation;


public class VendorActivity {

	public VendorRepresentation getVendorById(Integer id){
		Vendor vendor = VendorManager.getVendorById(id);
		return new VendorRepresentation(vendor);
	}
}
