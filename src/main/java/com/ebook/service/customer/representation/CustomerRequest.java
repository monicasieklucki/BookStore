package com.ebook.service.customer.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.customer.Address;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRequest {
	
	private Integer customerId;
	private Integer billingid;
	private Integer shippingid;
	private String lastName;
	private String firstName;
	private Address billingaddress;
	private Address shippingaddress;
	
	public Integer getBillingAddressId() {
		return billingid;
	}

	public void setBillingAddressId(Integer id) {
		this.billingid = id;
	}
	
	public Integer getShippingAddressId() {
		return shippingid;
	}

	public void setShippingAddressId(Integer id) {
		this.shippingid = id;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer id) {
		this.customerId = id;
	}
	public Address getBillingAddress() {
		return billingaddress;
	}

	public void setBillingAddress(Address billingaddress) {
		this.billingaddress = billingaddress;
	}

	public Address getShippingAddress() {
		return shippingaddress;
	}

	public void setShippingAddress(Address shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
