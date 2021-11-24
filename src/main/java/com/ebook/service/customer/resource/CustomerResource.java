package com.ebook.service.customer.resource;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;


import com.ebook.service.customer.representation.*;
import com.ebook.service.customer.workflow.CustomerActivity;
import com.ebook.model.customer.Customer;
import com.ebook.model.customer.Address;

@Path("/customerservice/")
public class CustomerResource {
	
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/customer/{customerid}")
	public CustomerRepresentation getCustomer(@PathParam("customerid") Integer id) {
		System.out.println("GET METHOD Request from Client with customerRequest Integer ............." + id);
		CustomerActivity custActivity = new CustomerActivity();
		return custActivity.getCustomer(id);
		
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Path("/customer")
	public CustomerRepresentation createCustomer(CustomerRequest customerRequest) { 
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		
		Customer customer = new Customer();
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());
		customer.setShippingAddress(customerRequest.getShippingAddress());
		customer.setBillingAddress(customerRequest.getBillingAddress());
		
		CustomerActivity custActivity = new CustomerActivity();
		return custActivity.createCustomer(customer);
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/customer/{customerid}")
	public Response deleteCustomer(CustomerRequest customerRequest,@PathParam("customerid") Integer customerid) {
		System.out.println("Delete METHOD Request from Client with customerRequest String ............." + customerRequest.getFirstName() + " " + customerRequest.getLastName());
		Customer customer = new Customer();
		customer.setCustomerId(customerid);
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());
		customer.setShippingAddress(customerRequest.getShippingAddress());
		customer.setBillingAddress(customerRequest.getBillingAddress());
		
		CustomerActivity custActivity = new CustomerActivity();
		String res = custActivity.deleteCustomer(customer);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/customer/{customerid}")
	public CustomerRepresentation updateEmployee(CustomerRequest customerRequest, @PathParam("customerid") Integer customerid) { 
		System.out.println("POST METHOD Request from Client with ............." + customerRequest.getFirstName() + "  " + customerRequest.getLastName());
		
		Customer customer = new Customer();
		customer.setCustomerId(customerid);
		customer.setFirstName(customerRequest.getFirstName());
		customer.setLastName(customerRequest.getLastName());
		customer.setShippingAddress(customerRequest.getShippingAddress());
		customer.setBillingAddress(customerRequest.getBillingAddress());
		
		Address shippingAddress = customerRequest.getShippingAddress();
		Address billingAddress = customerRequest.getBillingAddress();
		
		CustomerActivity custActivity = new CustomerActivity();
		return custActivity.updateCustomer(customer, shippingAddress, billingAddress);
	}

}
