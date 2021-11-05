package com.ebook.service.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ebook.model.customer.Customer;
import com.ebook.model.customer.Address;
import com.ebook.model.service.CustomerService;
import com.ebook.dal.CustomerDAO;
import com.ebook.service.representation.CustomerRepresentation;

public class CustomerActivity {
	
	private static CustomerService custService = new CustomerService();
	
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
