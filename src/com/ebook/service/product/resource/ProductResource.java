package com.ebook.service.product.resource;

import java.util.HashSet;
import java.util.List;
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

import com.ebook.service.product.representation.ProductRepresentation;
import com.ebook.service.product.workflow.ProductActivity;

@Path("/productservice/")
public class ProductResource {
	
	private static ProductActivity prodActivity = new ProductActivity();
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product")
	public Set<ProductRepresentation> getProducts() {
		return prodActivity.getProducts();
	}
	

	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/product/{productid}")
	public ProductRepresentation getProduct(@PathParam("productid") Integer id) {
		System.out.println("GET METHOD Request for product........");
		return prodActivity.getProduct(id);
	}
	
	@GET
	@Path("/product/availability/{productid}")
	public Response checkAvailability(@PathParam("productid") Integer id) {
		return Response.ok().build();
	}
}
