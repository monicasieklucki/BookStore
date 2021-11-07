package com.ebook.service.item.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.item.Product;

//TODO May not be used at all, remove it not needed
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRepresentation {	
	private Integer id;
	private String title;
	private Double price;
	
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
	public void setTitle(String name) {
		this.title = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
		
}
