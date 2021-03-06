package com.ebook.service.vendor.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.vendor.VendorLine;
import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;

@XmlRootElement(name = "VendorLine")
@XmlAccessorType(XmlAccessType.FIELD)
public class VendorLineRepresentation extends Representation {
	@XmlTransient
	private Integer vendorId;
	private Integer productId;
	private String productName;
	private Integer quantity;
	
	public VendorLineRepresentation() {}
	

	/**
	 * Creates a representation of the vendorLine. vendorId required for HATEOAS links. 
	 * @param vl vendorLine for representation
	 * @param vendorId id of vendor line is related to. 
	 */
	public VendorLineRepresentation(VendorLine vl, Integer vendorId) {
		this.productId = vl.getProduct().getId();
		this.productName = vl.getProduct().getTitle();
		this.quantity = vl.getQuantity();
		this.vendorId = vendorId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
}
