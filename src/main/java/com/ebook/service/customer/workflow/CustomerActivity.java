package com.ebook.service.customer.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ebook.model.customer.Customer;
import com.ebook.model.customer.CustomerManager;
import com.ebook.model.link.Link;
import com.ebook.service.customer.representation.CustomerRepresentation;
import com.ebook.model.customer.Address;
import com.ebook.dal.CustomerDAO;

public class CustomerActivity {
	
	private static CustomerManager custService = new CustomerManager();
	
	public CustomerRepresentation getCustomer(Integer id) {
		
		Customer cust = custService.findCustomerById(id);
		
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(cust.getFirstName());
		custRep.setBillingAddress(cust.getBillingAddress());
		custRep.setShippingAddress(cust.getShippingAddress());
		custRep.setLastName(cust.getLastName());
		
		return custRep;
	}
	
	public CustomerRepresentation createCustomer(Customer customer) {
		
		custService.addCustomer(customer);
		
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setCustomerId(customer.getCustomerId());
		custRep.setFirstName(customer.getFirstName());
		custRep.setLastName(customer.getLastName());
		custRep.setShippingAddress(customer.getShippingAddress());
		custRep.setBillingAddress(customer.getBillingAddress());
		return custRep;
	}
	
	public CustomerRepresentation createCustomerByUsername(Customer customer) {
		System.out.println("adding customer by username");
		System.out.println(customer);
		custService.addCustomerByUsername(customer);
		
		CustomerRepresentation custRep = new CustomerRepresentation();
		System.out.println(customer.getUsername());
		custRep.setUsername(customer.getUsername());
		
		Link UpdateCustomer = new Link("UpdateCustomer", "http://localhost:8080/service/customerservice/customer/"+customer.getCustomerId(), "json");
		Link DeleteCustomer = new Link("DeleteCustomer", "http://localhost:8080/service/customerservice/customer/"+customer.getCustomerId(),"json");
		custRep.setLinks(UpdateCustomer, DeleteCustomer);
		return custRep;
	}
	
	
	
	public String deleteCustomer(Customer customer) {
		
		custService.removeCustomerById(customer);
		
		return "OK";
	}
	
	public CustomerRepresentation updateCustomer(Customer customer, Address shippingAddress, Address billingAddress) {
		
		custService.updateCustomer(customer, shippingAddress, billingAddress);
		
		CustomerRepresentation custRep = new CustomerRepresentation();
		custRep.setFirstName(customer.getFirstName());
		custRep.setLastName(customer.getLastName());
		custRep.setShippingAddress(shippingAddress);
		custRep.setBillingAddress(billingAddress);
		
		return custRep;
	}


}
