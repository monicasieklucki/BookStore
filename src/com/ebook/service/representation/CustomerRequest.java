package com.ebook.service.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.customer.Address;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class CustomerRequest {
	
	private String lastName;
	private String firstName;
	private Address billingaddress;
	private Address shippingaddress;
	
	
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
