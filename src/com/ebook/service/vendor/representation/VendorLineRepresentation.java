package com.ebook.service.vendor.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "VendorLine")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class VendorLineRepresentation {
	private Integer VendorId;
	private String productName;
	private Double price;
	public Integer getVendorId() {
		return VendorId;
	}
	public void setVendorId(Integer vendorId) {
		VendorId = vendorId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
	
}
