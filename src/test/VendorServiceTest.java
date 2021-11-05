package test;

import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.model.vendor.VendorManager;

public class VendorServiceTest {

	public static void main(String[] args) {
		// Test adding a vendor
		Vendor vendor = VendorManager.addVendor("Barnes and Noble");
		Integer vendorId = vendor.getVendorId();
		System.out.printf("\nVendor added\n\tID: % d\n\n", vendorId);
		
		// Test retrieving a vendor
		Vendor retrievedVend = VendorManager.getVendorById(vendorId);
		System.out.printf("\nRetrieved vendor by id\n\tID: %d \n\tNAME: %s\n\n" , retrievedVend.getVendorId(), retrievedVend.getVendorName());
		
		// Test adding a couple product to the vendor
		VendorManager.addVendorProduct(vendorId, "Ready Player One", 11.99);
		VendorManager.addVendorProduct(vendorId, "To Kill a Mockingbird", 4.99);

		//Get vendor again to update list of products
		retrievedVend = VendorManager.getVendorById(vendorId);
		System.out.printf("\n\nNumber of Products for Vendor: %d\n\n" , retrievedVend.getVendorLines().size());
		for(VendorLine vl : retrievedVend.getVendorLines()) {
			Product product = vl.getProduct();
			System.out.printf("\nProduct added \n\tID: %d  \n\tName: %s \n\tPrice: $%.2f \n\tQuant: %d\n\n", product.getId(), product.getTitle(), product.getPrice(), vl.getQuantity() );
		}
		
		
		// Test deleting a vendor
		VendorManager.removeVendorById(vendorId);
		System.out.println("Attempted to retrieve vendor by ID null value expected:   " + VendorManager.getVendorById(vendorId).getVendorName());
		
		
	}

}
