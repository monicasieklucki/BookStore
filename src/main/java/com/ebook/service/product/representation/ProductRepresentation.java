package com.ebook.service.product.representation;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.item.Product;
import com.ebook.model.link.Link;
import com.ebook.model.vendor.Vendor;

import com.ebook.service.link.representation.LinkRepresentation;

@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation extends LinkRepresentation {
	private Integer id;
	private String title;
	private double price;
	
	public ProductRepresentation() {}
	
	public ProductRepresentation(Product product) {
		this.id = product.getId();
		this.title = product.getTitle();
		this.price = product.getPrice();

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
