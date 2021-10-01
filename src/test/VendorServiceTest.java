package test;

import com.ebook.model.item.Product;
import com.ebook.model.service.VendorService;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;

public class VendorServiceTest {

	public static void main(String[] args) {
		// Test adding a vendor
		Vendor vendor = VendorService.addVendor("Barnes and Noble");
		Integer vendorId = vendor.getVendorId();
		System.out.printf("\nVendor added\n\tID: % d\n\n", vendorId);
		
		// Test retrieving a vendor
		Vendor retrievedVend = VendorService.getVendorById(vendorId);
		System.out.printf("\nRetrieved vendor by id\n\tID: %d \n\tNAME: %s\n\n" , retrievedVend.getVendorId(), retrievedVend.getVendorName());
		
		// Test adding a couple product to the vendor
		VendorService.addVendorProduct(vendorId, "Ready Player One", 11.99);
		VendorService.addVendorProduct(vendorId, "To Kill a Mockingbird", 4.99);

		//Get vendor again to update list of products
		retrievedVend = VendorService.getVendorById(vendorId);
		System.out.printf("\n\nNumber of Products for Vendor: %d\n\n" , retrievedVend.getVendorLines().size());
		for(VendorLine vl : retrievedVend.getVendorLines()) {
			System.out.println("For loop ran");
			Product product = vl.getProduct();
			System.out.printf("\nProduct added \n\tID: %d  \n\tName: %s \n\tPrice: $%.2f \n\tQuant: %d\n\n", product.getId(), product.getTitle(), product.getPrice(), vl.getQuantity() );
		}
		
		
		// Test deleting a vendor
		VendorService.removeVendorById(vendorId);
		System.out.println("Attempted to retrieve vendor by ID null value expected:   " + VendorService.getVendorById(vendorId).getVendorName());
		
		
	}

}
