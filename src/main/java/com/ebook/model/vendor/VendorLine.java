package com.ebook.model.vendor;


import com.ebook.model.item.Product;

public class VendorLine {
	
	//partner inventory 
	private Product product;
	private int quantity;

	public VendorLine() {}
	
	public VendorLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

