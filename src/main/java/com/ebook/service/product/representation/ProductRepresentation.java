package com.ebook.service.product.representation;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.item.Product;
import com.ebook.model.vendor.Vendor;
import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;

/**
 * Product Representation will be used when customer is searching for product.
 * Vendors looking to manage products will do so from the vendor line.
 *
 */
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation extends Representation {
	private Integer id;
	private String title;
	private double price;
	
	public ProductRepresentation() {}
	
	public ProductRepresentation(Product product) {
		this.id = product.getId();
		this.title = product.getTitle();
		this.price = product.getPrice();
		super.addLink("self", String.format("productservice/product"), "application/json");
		super.addLink("self", String.format("productservice/product/%d", id), "application/json");
		//TODO order link
		//super.addLink("order/update", "orderservice/", "application/json")l
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	

}
