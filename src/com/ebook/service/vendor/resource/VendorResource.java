package com.ebook.service.vendor.resource;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorManager;
import com.ebook.service.vendor.representation.VendorRepresentation;
import com.ebook.service.vendor.workflow.VendorActivity;


@Path("/vendorservice/")
public class VendorResource {

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor/{vendorId}")
	public VendorRepresentation getVendor(@PathParam("vendorId") Integer id) {
		System.out.println("GET METHOD Request for vendor........");
		VendorActivity vendActivity = new VendorActivity();
		return vendActivity.getVendor(id);
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor")
	public VendorRepresentation addVendor(String vendorName) {
		
		Vendor vendor = VendorManager.addVendor(vendorName);
		
		VendorRepresentation vendRep = new VendorRepresentation(vendor);
		return vendRep;
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor/{vendorId}")
	public String removeVendor(Integer vendorId) {
		
		VendorManager.removeVendorById(vendorId);
		
		return "OK";
	}
	
	/*@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor/{vendorId}/{productTitle}/{productPrice}")*/
	/*public ProductRepresentation addVendorProduct(Integer vendorId, String productTitle, double productPrice) {
		
		Product product = VendorManager.addVendorProduct(vendorId, productTitle, productPrice)
		
		ProductRepresentation prodRep = new ProductRepresentation(product);
		return prodRep;
	}*/
	
}
