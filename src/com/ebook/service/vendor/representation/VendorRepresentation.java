package com.ebook.service.vendor.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.vendor.Vendor;

@XmlRootElement(name = "Vendor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class VendorRepresentation {	
	private Integer vendorID;
	private String vendorName;
	
	public VendorRepresentation() {}
	
	public VendorRepresentation(Vendor vendor) {
		this.vendorID = vendor.getVendorId();
		this.vendorName = vendor.getVendorName();
	}

	public Integer getVendorID() {
		return vendorID;
	}

	public void setVendorID(Integer vendorID) {
		this.vendorID = vendorID;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}	
	
}
