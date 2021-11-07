package com.ebook.service.vendor.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ebook.model.customer.Customer;
import com.ebook.model.customer.CustomerManager;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorManager;
import com.ebook.service.representation.CustomerRepresentation;
import com.ebook.service.vendor.representation.VendorLineRequest;
import com.ebook.service.vendor.representation.VendorRepresentation;

public class VendorActivity {
	
	public Set<VendorRepresentation> getVendors() {
		Set<Vendor> vendors = new HashSet<Vendor>();
		Set<VendorRepresentation> vendReps = new HashSet<VendorRepresentation>();
		
		vendors = VendorManager.getVendors();
		for(Vendor vend : vendors) {
			vendReps.add(new VendorRepresentation(vend));
		}
		
		return vendReps;
	}

	public VendorRepresentation getVendor(Integer id) {
		Vendor vendor = VendorManager.getVendorById(id);
		return new VendorRepresentation(vendor);
	}

	public VendorRepresentation addVendor(String vendorName) {
		Vendor vendor = VendorManager.addVendor(vendorName);
		VendorRepresentation vendRep = new VendorRepresentation(vendor);
		return vendRep;
	}

	public String removeVendor(Integer vendorId) {
		VendorManager.removeVendorById(vendorId);
		return "OK";
	}

	public String addVendorLine(VendorLineRequest vendRequest) {
		VendorManager.addVendorProduct(vendRequest.getVendorId(), vendRequest.getProductTitle(),
				vendRequest.getProductPrice());
		
		return "OK";
	}

}
