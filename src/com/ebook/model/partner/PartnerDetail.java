package com.ebook.model.partner;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.item.Product;

public class PartnerDetail {
	
	//partner inventory 
	private Product product;
	private int quantity;

	public PartnerDetail() {}
	
	public PartnerDetail(Product product, int quantity) {
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
