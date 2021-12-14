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
import com.ebook.model.vendor.VendorLine;
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

	public VendorRepresentation getVendor(Integer id) {
		Vendor vendor = VendorManager.getVendorById(id);
		VendorRepresentation vendRep =  new VendorRepresentation(vendor);
		// Add the links to each vendor line
		for(VendorLineRepresentation vl : vendRep.getVendorLines()) {
			BookStoreUri getProduct = new BookStoreUri("product", String.format("service/productservice/product/%d", vl.getVendorId()),"application/json");
			BookStoreUri delVendLine = new BookStoreUri("delete", String.format("service/vendorservice/vendor/product?vendorId=%d&productId=%d",vl.getVendorId(), vl.getProductId())); 
			vl.addLinks(getProduct,delVendLine);
		}
		// Add links at vendor level
		BookStoreUri modifyVendorLink = new BookStoreUri("vendor/modify", String.format("service/vendorservice/vendor/%d", vendRep.getVendorId()), "application/json");
		BookStoreUri deleteVendorLink = new BookStoreUri("vendor/delete", String.format("service/vendorservice/vendor/%d", vendRep.getVendorId()));
		BookStoreUri getAllOrders = new BookStoreUri("orders", String.format("service/orderservice/order?vendorid=%d&statuses=ordered,shipped,canceled", vendRep.getVendorId()),"application/json");
		BookStoreUri addProduct = new BookStoreUri("vendor/product/add", "service/vendorservice/vendor/product", "application/json");
		vendRep.addLinks(modifyVendorLink, deleteVendorLink, getAllOrders, addProduct);
		return vendRep;
	}
	
	public VendorLineRepresentation getVendorlineByProductId(Integer id) {
		
		VendorLine vendorline = VendorManager.getVendorlineByProductId(id);
		VendorLineRepresentation vendLineRep = new VendorLineRepresentation();
		vendLineRep.setProductId(id);
		vendLineRep.setQuantity(vendorline.getQuantity());
		vendLineRep.setProductName(vendorline.getProduct().getTitle());
		vendLineRep.setVendorId(vendorline.getVendorId());
		
		return vendLineRep;
	}

	public VendorRepresentation addVendor(VendorRequest vendReq) {
		Vendor vendor = VendorManager.addVendor(vendReq.getVendorName());
		VendorRepresentation rep =  new VendorRepresentation(vendor);
		// Add Links
		BookStoreUri modifyVendorLink = new BookStoreUri("vendor/modify", String.format("service/vendorservice/vendor/%d", rep.getVendorId()), "application/json");
		BookStoreUri deleteVendorLink = new BookStoreUri("vendor/delete", String.format("service/vendorservice/vendor/%d", rep.getVendorId()));
		BookStoreUri getAllOrders = new BookStoreUri("orders", String.format("service/orderservice/order?vendorid=%d&statuses=ordered,shipped,canceled", rep.getVendorId()),"application/json");
		BookStoreUri addProduct = new BookStoreUri("vendor/product/add", "service/vendorservice/vendor/product", "application/json");
		rep.addLinks(modifyVendorLink, deleteVendorLink, getAllOrders, addProduct);
		return rep;
	}

	public VendorLineRepresentation addVendorLine(VendorLineRequest vendLineReq) {
		Product newProduct = VendorManager.addVendorProduct(vendLineReq.getVendorId(), vendLineReq.getProductTitle(),
				vendLineReq.getProductPrice());	
		VendorLineRepresentation vendLineRep = new VendorLineRepresentation();
		vendLineRep.setVendorId(vendLineReq.getVendorId());
		vendLineRep.setProductId(newProduct.getId());
		vendLineRep.setProductName(vendLineReq.getProductTitle());	
		// Add the links
		BookStoreUri getProduct = new BookStoreUri("product", String.format("service/productservice/product/%d", vendLineRep.getVendorId()),"application/json");
		BookStoreUri delVendLine = new BookStoreUri("delete", String.format("service/vendorservice/vendor/product?vendorId=%d&productId=%d",vendLineRep.getVendorId(), vendLineRep.getProductId())); 
		vendLineRep.addLinks(getProduct,delVendLine);
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
			VendorSummaryRepresentation vendSum = new VendorSummaryRepresentation(v);
			BookStoreUri getVendorDetail = new BookStoreUri("self", String.format("service/vendorservice/vendor/%d", vendSum.getVendorId()),"application/json");
			vendSum.addLinks(getVendorDetail);
			vendors.add(vendSum);
		}
		VendorListRepresentation rep = new VendorListRepresentation(vendors);
		rep.addLink("vendor", "vendorservice/vendor", "application/json");
		return rep;
	}

}
