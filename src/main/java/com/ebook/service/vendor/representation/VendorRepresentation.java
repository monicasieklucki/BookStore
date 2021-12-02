package com.ebook.service.vendor.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;


@XmlRootElement(name = "Vendor")
@XmlAccessorType(XmlAccessType.FIELD)
public class VendorRepresentation extends Representation {	
	@XmlElement(name="vendorId")
	private Integer vendorID;
	@XmlElement(name="vendor")
	private String vendorName;
	@XmlElementWrapper(name="vendorLines")
	@XmlElement(name="vendorLine")
	private List<VendorLine> vendorLines;
	
	public VendorRepresentation() {}
		
	public VendorRepresentation(Vendor vendor) {
		this.vendorID = vendor.getVendorId();
		this.vendorName = vendor.getVendorName();
		if(vendor.getVendorLines().size() > 0) {
			this.vendorLines = vendor.getVendorLines();
		}
		// Links that should go on all representations of a vendor added here
		BookStoreUri modifyVendorLink = new BookStoreUri("vendor/modify", String.format("service/vendorservice/vendor/%d", getVendorID()), "application/xml");
		BookStoreUri deleteVendorLink = new BookStoreUri("vendor/delete", String.format("service/vendorservice/vendor/%d", getVendorID()));
		super.addLinks(modifyVendorLink, deleteVendorLink);
	}

	public Integer getVendorID() {
		return vendorID;
	}

	public String getVendorName() {
		return vendorName;
	}

	public List<VendorLine> getVendorLines() {
		return vendorLines;
	}
	
}
