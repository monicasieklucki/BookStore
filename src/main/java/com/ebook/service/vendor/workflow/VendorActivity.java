package com.ebook.service.vendor.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriInfo;

import com.ebook.model.customer.Customer;
import com.ebook.model.customer.CustomerManager;
import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorManager;
import com.ebook.service.customer.representation.CustomerRepresentation;
import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;
import com.ebook.service.vendor.representation.VendorLineRepresentation;
import com.ebook.service.vendor.representation.VendorLineRequest;
import com.ebook.service.vendor.representation.VendorListRepresentation;
import com.ebook.service.vendor.representation.VendorRepresentation;
import com.ebook.service.vendor.representation.VendorRequest;
import com.ebook.service.vendor.representation.VendorSummaryRepresentation;

public class VendorActivity {
	// Can probably remove this. When getting the full list, we will return jut the summary representation.
//	public Set<VendorRepresentation> getVendors() {
//		Set<Vendor> vendors = new HashSet<Vendor>();
//		Set<VendorRepresentation> vendReps = new HashSet<VendorRepresentation>();
//		
//		vendors = VendorManager.getVendors();
//		System.out.println("VendorActivity: Number of vendors returned " + vendors.size() );
//		for(Vendor vend : vendors) {
//			vendReps.add(new VendorRepresentation(vend));
//		}
//		
//		return vendReps;
//	}

	public VendorRepresentation getVendor(Integer id) {
		Vendor vendor = VendorManager.getVendorById(id);
		VendorRepresentation vendRep =  new VendorRepresentation(vendor);
		return vendRep;
	}

	public VendorRepresentation addVendor(VendorRequest vendReq) {
		Vendor vendor = VendorManager.addVendor(vendReq.getVendorName());
		return new VendorRepresentation(vendor);
	}

	public VendorLineRepresentation addVendorLine(VendorLineRequest vendLineReq) {
		Product newProduct = VendorManager.addVendorProduct(vendLineReq.getVendorId(), vendLineReq.getProductTitle(),
				vendLineReq.getProductPrice());	
		VendorLineRepresentation vendLineRep = new VendorLineRepresentation();
		vendLineRep.setVendorId(vendLineReq.getVendorId());
		vendLineRep.setProductId(newProduct.getId());
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
	
	/**
	 * Returns a wrapper with all of the vendor representations. 
	 * Adds HATEOAS links for creation of new vendors. 
	 * @return
	 */
	public VendorListRepresentation getVendorsList() {
		List<VendorSummaryRepresentation> vendors = new ArrayList<>();
		for(Vendor v : VendorManager.getVendors()) {
			vendors.add(new VendorSummaryRepresentation(v));
		}
		VendorListRepresentation rep = new VendorListRepresentation(vendors);
		return rep;
	}

}
