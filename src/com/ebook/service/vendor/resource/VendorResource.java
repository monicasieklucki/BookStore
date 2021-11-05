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
		return vendActivity.getVendorById(id);
	}
	
}
