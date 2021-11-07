package com.ebook.service.item.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//TODO May not be used ever, remove if not needed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ProductRequest {
	private String title;
	private Double price;
	
	public ProductRequest() {};
	

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
