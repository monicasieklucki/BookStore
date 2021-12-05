package com.ebook.service.vendor.representation;

import java.util.ArrayList;
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
	private Integer vendorId;
	@XmlElement(name="vendor")
	private String vendorName;
	@XmlElementWrapper(name="vendorLines")
	@XmlElement(name="vendorLine")
	private List<VendorLineRepresentation> vendorLineReps = new ArrayList<VendorLineRepresentation>();
	
	public VendorRepresentation() {}
		
	public VendorRepresentation(Vendor vendor) {
		this.vendorId = vendor.getVendorId();
		this.vendorName = vendor.getVendorName();
		if(vendor.getVendorLines().size() > 0) {
			for(VendorLine vl : vendor.getVendorLines()) {
				this.vendorLineReps.add(new VendorLineRepresentation(vl,vendorId));
			}
		}
		// Links that should go on all representations of a vendor added here
		BookStoreUri modifyVendorLink = new BookStoreUri("vendor/modify", String.format("service/vendorservice/vendor/%d", getVendorId()), "application/json");
		BookStoreUri deleteVendorLink = new BookStoreUri("vendor/delete", String.format("service/vendorservice/vendor/%d", getVendorId()));
		BookStoreUri getAllOrders = new BookStoreUri("orders", String.format("service/orderservice/order?vendorid=%d&statuses=ordered,shipped,canceled", getVendorId()),"application/json");
		super.addLinks(modifyVendorLink, deleteVendorLink, getAllOrders);
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public List<VendorLineRepresentation> getVendorLines() {
		return vendorLineReps;
	}
	
}
