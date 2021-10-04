package test;

import com.ebook.model.customer.Address;
import com.ebook.model.customer.Customer;
import com.ebook.model.item.Product;
import com.ebook.model.service.CustomerService;
import com.ebook.model.service.VendorService;
import com.ebook.model.vendor.Vendor;

public class CustomerServiceTest {

	public static void main(String[] args) {
		//Client will use the customer service to have access to anything related to customer functionality.
	System.out.println("*************** Creating Customer service object *************************");
	CustomerService custService = new CustomerService();    

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
	}

}
