package com.ebook.view;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.customer.Address;
import com.ebook.model.customer.Customer;
import com.ebook.model.customer.CustomerManager;
import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.model.vendor.VendorManager;

public class BookStoreClient2 {
	public static void main (String args[]) throws Exception {
		
		//Client will use the customer service to have access to anything related to customer functionality.
	System.out.println("*************** Creating Customer service object *************************");
	CustomerManager custService = new CustomerManager();    

	System.out.println("BookStoreClient2: *************** instantiating a customer and its address *************************");
    Customer customer = new Customer();
    customer.setCustomerId(2);
	customer.setFirstName("John");
    customer.setLastName("Doe");
    
    Address address = new Address();
    address.setAddressId(2);
    address.setStreet("500 West Madison St.");
    address.setUnit("Suite 101");
    address.setCity("Chicago");
    address.setState("IL");
    address.setZip("66610");
    
    customer.setBillingAddress(address);
    customer.setShippingAddress(address);

      
    //save customer information
    //Saving the newly created customer and its address
    custService.addCustomer(customer);
        
    System.out.println("BookStoreClient2: *************** Customer is inserted in BookStore Database *************************");

    System.out.println("BookStoreClient2: *************** trying to search customer in the database *************************");
 
    //Find a customer if already exists; if not, create a new one.
    Customer searchedCustomer = custService.findCustomerById(2); 
      
    System.out.println("BookStoreClient2: *************** Here is searched customer information *************************");
    try {
    System.out.println("\tID: \t\t\t " + searchedCustomer.getCustomerId() + "\tName: \t\t\t" + searchedCustomer.getFirstName() + " " + searchedCustomer.getLastName() + "\n");
    } catch (Exception e) {
      System.out.println(e);
    }
    Address billingAdd = searchedCustomer.getBillingAddress();
    System.out.println("\tBilling Address:\t" + billingAdd.getAddressId() + 
        "\n\t\t\t\t" + billingAdd.getStreet() +
        "\n\t\t\t\t" + billingAdd.getUnit() + 
        "\n\t\t\t\t" + billingAdd.getCity() + ", " + 
        billingAdd.getState() + " " + billingAdd.getZip() +
        "\n");
    
    //delete customer and address
    //custService.removeCustomerById(1);
    
    //update customer and address
    //custService.updateCustomer(customer, address);
    
	//Client will use the customer service to have access to anything related to customer functionality.
    System.out.println("*************** Creating Vendor service object *************************");
    VendorManager vendService = new VendorManager();  
    
	System.out.println("BookStoreClient2: *************** instantiating a Vendor, VendorLine, and Product *************************");
	// creating products
	// can only sell products that are in the product table
	Product prod1 = new Product();
	prod1.setId(4);
	prod1.setTitle("Of Mice & Men");
	prod1.setPrice(15.99);
	
	Product prod2 = new Product();
	prod2.setId(5);
	prod2.setTitle("East of Eden");
	prod2.setPrice(12.00);
	
	Product prod3 = new Product();
	prod3.setId(6);
	prod3.setTitle("The Boys in the Boat");
	prod3.setPrice(9.99);
	
	
    Vendor vendor = new Vendor();
    vendor.setVendorId(3);
    vendor.setVendorName("Amazon");
    
//    //save vendor information
//    //Saving the newly created vendor 
//    vendService.addVendor(vendor);
//    System.out.println("BookStoreClient2: *************** Vendor is inserted in BookStore Database *************************");
//
//   
//    VendorLine vendorline1 = new VendorLine();
//    vendorline1.setQuantity(0);
//    vendorline1.setProduct(prod1);
//    vendor.setVendorLine(vendorline1);
//    vendService.addVendorProduct(vendor);
//    System.out.println("BookStoreClient2: *************** VendorLine is added in BookStore Database *************************");
//
//    
//    VendorLine vendorline2 = new VendorLine();
//    vendorline2.setQuantity(3);
//    vendorline2.setProduct(prod2);
//    vendor.setVendorLine(vendorline2);
//    vendService.addVendorProduct(vendor);
//    System.out.println("BookStoreClient2: *************** VendorLine is added in BookStore Database *************************");


    //remove vendor and vendor lines
    //vendService.removeVendorById(3);
    //System.out.println("BookStoreClient2: *************** Vendor is removed in BookStore Database *************************");
        
    	
        
	}
}
