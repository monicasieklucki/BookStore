package com.ebook.service.vendor.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ebook.model.vendor.Vendor;
import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="vendor")
public class VendorSummaryRepresentation extends Representation {
	public Integer vendorId;
	public String vendorName;
	
	public VendorSummaryRepresentation() {}
	
	public VendorSummaryRepresentation(Vendor vend) {
		this.vendorId = vend.getVendorId();
		this.vendorName = vend.getVendorName();
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
}
