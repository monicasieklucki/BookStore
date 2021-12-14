package com.ebook.model.vendor;

import com.ebook.model.item.Product;

public class VendorLine {
	
	//partner inventory 
	private Product product;
	private int productid;
	private int quantity;
	private int vendorid;

	public VendorLine() {}
	
	public VendorLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setVendorId(Integer vendorid) {
		this.vendorid = vendorid;
	}
	
	public Integer getVendorId() {
		return vendorid;
	}
		
	public Integer getProductId() {
		return productid;
	}
	
	public void setProductId(Integer productid) {
		this.productid = productid;
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

