package test;

import com.ebook.model.service.VendorService;
import com.ebook.model.vendor.Vendor;

public class VendorServiceTest {

	public static void main(String[] args) {
		Vendor vendor = VendorService.addVendor("Barnes and Noble");
		Integer vendorId = vendor.getVendorId();
		System.out.println("Vendor added with ID = " + vendorId);
		Vendor retrievedVend = VendorService.getVendorById(vendorId);
		System.out.println("Retrieved vendor by id NAME: " + retrievedVend.getVendorName() + " ID: " + retrievedVend.getVendorId());


	}

}
