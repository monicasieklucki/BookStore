package com.ebook.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.model.customer.Address;
import com.ebook.model.customer.Customer;

public class CustomerDAO {
	
	public CustomerDAO() {}
	
	public Customer getCustomer(Integer customerId) {	 	 
	    try { 		
	    	//Get Customer
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectCustomerQuery = "SELECT customerid, lastname, firstname, billingaddress, shippingaddress FROM Customer WHERE customerid = " + customerId + ";";

	    	ResultSet custRS = st.executeQuery(selectCustomerQuery);      
	    	System.out.println("CustomerDAO: *************** Query " + selectCustomerQuery);
	    	
	      //Get Customer
    	  Customer customer = new Customer();
	      while ( custRS.next() ) {
	    	  customer.setCustomerId(custRS.getInt("customerid"));
	    	  customer.setLastName(custRS.getString("lastName"));
	    	  customer.setFirstName(custRS.getString("firstName"));
	    	  customer.setBillingAddressId(custRS.getInt("billingaddress"));
	    	  customer.setShippingAddressId(custRS.getInt("shippingaddress"));
	      }
	      //close to manage resources
	      custRS.close();
	      
	      customer.setBillingAddress(getCustomerAddress(customer.getBillingAddressId(), st));
	      customer.setShippingAddress(getCustomerAddress(customer.getShippingAddressId(), st));

          st.close();
	      
	      return customer;
	     
	    }	    
	    catch (SQLException se) {
	      System.err.println("CustomerDAO: Threw a SQLException retrieving the customer object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }   
	    return null;
	  }

	public Address getCustomerAddress(Integer id, Statement st) {
	 	 
	    try { 
		      //Get Address	      
		      String selectAddressQuery = "SELECT addressid, street, unit, city, state, zip FROM Address WHERE customerid = '" + id + "'";
		      ResultSet addRS = st.executeQuery(selectAddressQuery);
	    	  Address address = new Address();
	    	  
	    	  System.out.println("CustomerDAO: *************** Query " + selectAddressQuery);
	    	  
		      while ( addRS.next() ) {
		    	  address.setAddressId(addRS.getInt("addressid"));
		    	  address.setStreet(addRS.getString("street"));
		    	  address.setUnit(addRS.getString("unit"));
		    	  address.setCity(addRS.getString("city"));
		    	  address.setState(addRS.getString("state"));
		    	  address.setZip(addRS.getString("zip"));
		      }
		     	      
		     //close to manage resources
		     addRS.close();
		     
		     return address;
	    	
	    }
	    catch (SQLException se) {
		      System.err.println("CustomerDAO: Threw a SQLException retrieving the customer object.");
		      System.err.println(se.getMessage());
		      se.printStackTrace();
	    }
	    
	    return null;
	}
	
	public void addCustomer(Customer cust) {
		Connection con = DBHelper.getConnection();
        PreparedStatement custPst = null;
        PreparedStatement addPst = null;

        try {
        	//Insert the customer object
            String custStm = "INSERT INTO Customer(customerID, lastname, firstname) VALUES(?, ?, ?)";
            custPst = con.prepareStatement(custStm);
            custPst.setInt(1, cust.getCustomerId());
            custPst.setString(2, cust.getLastName());       
            custPst.setString(3, cust.getFirstName()); 
            custPst.executeUpdate();
                        
           //Insert the customer address object - billing
           String addStm = "INSERT INTO Address(addressid, street, unit, city, state, zip, customerid) VALUES(?, ?, ?, ?, ?, ?, ?)";
           addPst = con.prepareStatement(addStm);
           addPst.setInt(1, cust.getBillingAddress().getAddressId());  
           addPst.setString(2, cust.getBillingAddress().getStreet());       
           addPst.setString(3, cust.getBillingAddress().getUnit());  
           addPst.setString(4, cust.getBillingAddress().getCity());  
           addPst.setString(5, cust.getBillingAddress().getState());      
           addPst.setString(6, cust.getBillingAddress().getZip()); 
           
           addPst.executeUpdate();
           
           //Insert the customer address object - shipping
           addPst.setInt(1, cust.getShippingAddress().getAddressId());  
           addPst.setString(2, cust.getShippingAddress().getStreet());       
           addPst.setString(3, cust.getShippingAddress().getUnit());  
           addPst.setString(4, cust.getShippingAddress().getCity());  
           addPst.setString(5, cust.getShippingAddress().getState());      
           addPst.setString(6, cust.getShippingAddress().getZip()); 

           addPst.executeUpdate();   
           
            
        } catch (SQLException ex) {

        } finally {

            try {
                if (addPst != null) {
                	addPst.close();
                	custPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
      	      System.err.println("CustomerDAO: Threw a SQLException saving the customer object.");
    	      System.err.println(ex.getMessage());
            }
        }
    }
	
	public void removeCustomer(Customer customer) {
		try {
			
	    	Statement st = DBHelper.getConnection().createStatement();

	    	//Delete Customer Billing Address
	    	String deleteBAddressQuery = "DELETE FROM Address WHERE addressid = " + customer.getBillingAddressId() + ";";
	    	st.executeUpdate(deleteBAddressQuery);
	    	System.out.println("CustomerDAO: *************** Query " + deleteBAddressQuery);
	    	
	    	//Delete Customer Shipping Address
	    	String deleteSAddressQuery = "DELETE FROM Address WHERE addressid = " + customer.getShippingAddressId() + ";";
	    	st.executeUpdate(deleteSAddressQuery);
	    	
	    	//Delete Customer
	    	String deleteCustomerQuery = "DELETE FROM Customer WHERE customerID = " + customer.getCustomerId() + ";";

	    	st.executeUpdate(deleteCustomerQuery);
	    	System.out.println("CustomerDAO: *************** Query " + deleteCustomerQuery);
	    	

	    	st.close();

		}
		catch (SQLException ex) {
			System.err.println("CustomerDAO: Threw a SQLException deleting the customer object.");
    	    System.err.println(ex.getMessage());
		}
	}
	
	public void updateCustomer(Customer customer, Address billingaddress, Address shippingaddress) {
		try {
	    	//Update Customer
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String updateCustomerQuery = "UPDATE customer SET lastname = '" + customer.getLastName() + "',firstname = '" + customer.getFirstName() + "' WHERE customerid = '" + customer.getCustomerId() + "'";
	    	st.executeUpdate(updateCustomerQuery);
	    	System.out.println("CustomerDAO: *************** Query " + updateCustomerQuery);
	    	
	    	//Update Customer Billing and Shipping Address
	    	String updateCustomerBAddressQuery = "UPDATE address SET street = '" + billingaddress.getStreet() + "',unit = '" + billingaddress.getUnit() + "', city= '" + billingaddress.getCity() + "', state= '" + billingaddress.getState() + "', zip= '" + billingaddress.getZip() + "' WHERE customerid = '" + billingaddress.getAddressId() + "'";
	    	st.executeUpdate(updateCustomerBAddressQuery);
	    	System.out.println("CustomerDAO: *************** Query " + updateCustomerBAddressQuery);
	    	
	    	String updateCustomerSAddressQuery = "UPDATE address SET street = '" + shippingaddress.getStreet() + "',unit = '" + shippingaddress.getUnit() + "', city= '" + shippingaddress.getCity() + "', state= '" + shippingaddress.getState() + "', zip= '" + shippingaddress.getZip() + "' WHERE customerid = '" + shippingaddress.getAddressId() + "'";
	    	st.executeUpdate(updateCustomerSAddressQuery);
	    	System.out.println("CustomerDAO: *************** Query " + updateCustomerSAddressQuery);
	    	
	    	st.close();
		}
		catch (SQLException ex) {
      	    System.err.println("CustomerDAO: Threw a SQLException updating the customer object.");
    	    System.err.println(ex.getMessage());
		}
	}
}
