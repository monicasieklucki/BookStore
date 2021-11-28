package com.ebook.service.vendor.representation;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.link.Link;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;


@XmlRootElement(name = "Vendor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class VendorRepresentation {	
	private Integer vendorID;
	private String vendorName;
	private List<VendorLine> vendorLines;
	
	public VendorRepresentation() {}
	
	public VendorRepresentation(Vendor vendor) {
		this.vendorID = vendor.getVendorId();
		this.vendorName = vendor.getVendorName();
		if(vendor.getVendorLines().size() > 0) {
			this.vendorLines = vendor.getVendorLines();
		}
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

	public List<VendorLine> getVendorLines() {
		return vendorLines;
	}

	public void setVendorLines(List<VendorLine> vendorLines) {
		this.vendorLines = vendorLines;
	}	
	
	protected List<Link> links;
	
	public List<Link> getLinks() {
		return links;
	}
	
	public void setLinks(Link...links) {
		this.links = Arrays.asList(links);
	}
	
}
