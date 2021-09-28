package com.ebook.model.service;

import com.ebook.dal.CustomerDAO;
import com.ebook.model.customer.Customer;
import com.ebook.model.customer.Address;

public class CustomerService {
	private CustomerDAO custDAO = new CustomerDAO();
	
	//search customer by ID from the DB
	public Customer findCustomerById(Integer customerId) {
		
		Customer customer = null;
			
		try {
			customer = custDAO.getCustomer(customerId);
	    	return customer;
	    } catch (Exception se) {
	      System.err.println("CustomerService: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
		return customer;
	}
	
	//Insert a new customer in the DB
	public void addCustomer(Customer customer) {
		
		try {
			custDAO.addCustomer(customer);
	    } catch (Exception se) {
	      System.err.println("CustomerService: Threw a Exception retrieving customer.");
	      System.err.println(se.getMessage());
	    }
	}
	
	
	//Delete customer from DB
	public void removeCustomerById(Customer customer) {
		
		try {
			custDAO.removeCustomer(customer.getCustomerId());
	    } catch (Exception se) {
	      System.err.println("CustomerService: Threw a Exception removing customer.");
	      System.err.println(se.getMessage());
	    }
	}
	//Update customer in DB
	public void updateCustomer(Customer customer, Address address) {	
		try {
			custDAO.updateCustomer(customer, address);
	    } catch (Exception se) {
	      System.err.println("CustomerService: Threw a Exception updating customer.");
	      System.err.println(se.getMessage());
	    }
	}
	
}
