package com.ebook.service.vendor.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ebook.model.customer.Customer;
import com.ebook.model.customer.CustomerManager;
import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorManager;
import com.ebook.service.customer.representation.CustomerRepresentation;
import com.ebook.service.vendor.representation.VendorLineRepresentation;
import com.ebook.service.vendor.representation.VendorLineRequest;
import com.ebook.service.vendor.representation.VendorRepresentation;
import com.ebook.service.vendor.representation.VendorRequest;

public class VendorActivity {
	
	public Set<VendorRepresentation> getVendors() {
		Set<Vendor> vendors = new HashSet<Vendor>();
		Set<VendorRepresentation> vendReps = new HashSet<VendorRepresentation>();
		
		vendors = VendorManager.getVendors();
		System.out.println("VendorActivity: Number of vendors returned " + vendors.size() );
		for(Vendor vend : vendors) {
			vendReps.add(new VendorRepresentation(vend));
		}
		
		return vendReps;
	}

	public VendorRepresentation getVendor(Integer id) {
		Vendor vendor = VendorManager.getVendorById(id);
		return new VendorRepresentation(vendor);
	}

	public VendorRepresentation addVendor(VendorRequest vendReq) {
		Vendor vendor = VendorManager.addVendor(vendReq.getVendorName());
		VendorRepresentation vendRep = new VendorRepresentation(vendor);
		return vendRep;
	}

	public VendorLineRepresentation addVendorLine(VendorLineRequest vendLineReq) {
		Product newProduct = VendorManager.addVendorProduct(vendLineReq.getVendorId(), vendLineReq.getProductTitle(),
				vendLineReq.getProductPrice());	
		VendorLineRepresentation vendLineRep = new VendorLineRepresentation();
		vendLineRep.setVendorId(vendLineReq.getVendorId());
		vendLineRep.setProductId(newProduct.getId());
		vendLineRep.setPrice(vendLineReq.getProductPrice());
		vendLineRep.setProductName(vendLineReq.getProductTitle());
		return vendLineRep;
	}
	
	public String removeVendor(Integer vendorId) {
		VendorManager.removeVendorById(vendorId);	
		return "OK";
	}

	public String removeVendorLine(Integer vendorId, Integer productId) {
		VendorManager.removeVendorLine(vendorId, productId);
		return "OK";
	}

}
