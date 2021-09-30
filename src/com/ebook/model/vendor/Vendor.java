package com.ebook.model.vendor;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.item.Product;
import com.ebook.model.order.OrderLine;
import com.ebook.model.vendor.VendorLine;

public class Vendor {
	
	private Integer vendorId;
	private String vendorName;
	
	private VendorLine vendorLine;
	private List<VendorLine> vendorLines = new ArrayList<VendorLine>();
	
	public Vendor() {}
	
	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
		
	public List<VendorLine> getVendorLines() {
		return vendorLines;
	}
	
	public VendorLine getVendorLine() {
		return vendorLine;
	}

	public void setVendorLine(VendorLine vendorLine) {
		this.vendorLine = vendorLine;
	}

	public void setVendorLines(List<VendorLine> vendorLines) {
		this.vendorLines = vendorLines;
	}
	
	public void addVendorProduct(Product product, int quantity) {
		vendorLines.add(new VendorLine(product, quantity));
		 
	}
}
