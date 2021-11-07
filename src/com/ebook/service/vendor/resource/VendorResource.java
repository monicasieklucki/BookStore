package com.ebook.service.vendor.resource;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorManager;
import com.ebook.service.vendor.representation.VendorLineRepresentation;
import com.ebook.service.vendor.representation.VendorLineRequest;
import com.ebook.service.vendor.representation.VendorRepresentation;
import com.ebook.service.vendor.representation.VendorRequest;
import com.ebook.service.vendor.workflow.VendorActivity;


@Path("/vendorservice/")
public class VendorResource {
	private static VendorActivity vendActivity = new VendorActivity();
	
	/**
	 * Returns simplified vendor representations that only include id and name
	 * @return
	 */
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor")
	public Set<VendorRepresentation> getVendors() {
		return vendActivity.getVendors();
	}
	
	/**
	 * Returns a detailed vendor representation with all products/quantities
	 * @param id id of vendor to retrieve
	 * @return Complete representation with products and quantities
	 */
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor/{vendorId}")
	public VendorRepresentation getVendor(@PathParam("vendorId") Integer id) {
		System.out.println("GET METHOD Request for vendor........");
		return vendActivity.getVendor(id);
	}
	
	/**
	 * Creates a vendor with the name provided in the request body. Returns the
	 * representation which includes the domain generated Id.
	 * @param req VendorRequest with only a name to be added
	 * @return VendorRepresentation including generated ID
	 */
	@POST
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/vendor")
	public VendorRepresentation addVendor(VendorRequest req) {		
		return vendActivity.addVendor(req);
	}
	
	/**
	 * Adds a vendorLine for a given product. Quantity assumed to be 1 based on
	 * underlying application layer
	 * @param vendLineReq Contains details like product name, price and related vendor
	 * @return VendorLineRepresentation which includes the newly generated productId
	 */
	@POST
	@Consumes({"application/xml" , "application/json"})
	@Path("/vendor/product")
	public VendorLineRepresentation addProduct(VendorLineRequest vendLineReq) {
		VendorActivity vendAct = new VendorActivity();
		VendorLineRepresentation res = vendAct.addVendorLine(vendLineReq);
		return res;	
	}
	
	//Returns OK even if vendor doens't exist. Should be fine? Code for resource not found?
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor/{vendorId}")
	public Response removeVendor(@PathParam("vendorId") Integer vendorId) {
		String res = vendActivity.removeVendor(vendorId);
		if(res.equals("OK")) {
			return Response.status(Status.OK).build();
		}	
		return null;
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Path("/vendor/product")
	public Response removeProduct(@QueryParam("vendorId") Integer vendId, @QueryParam("productId") Integer productId) {
		String res = vendActivity.removeVendorLine(vendId, productId);
		if(res.equals("OK")) {
			return Response.status(Status.OK).build();
		}	
		return null;
	}


	
}
