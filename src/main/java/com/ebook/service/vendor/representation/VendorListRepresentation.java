package com.ebook.service.vendor.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ebook.service.util.Representation;

@XmlRootElement(name="vendors")
@XmlAccessorType(XmlAccessType.FIELD)
public class VendorListRepresentation extends Representation {
	@XmlElement(name="resultCount")
	public Integer resultCount;
	
	@XmlElementWrapper(name="data")
	@XmlElement(name="vendor")
	public List<VendorSummaryRepresentation> vendors;
	
	public VendorListRepresentation() {}
	
	public VendorListRepresentation(List<VendorSummaryRepresentation> vendorReps) {
		this.vendors = vendorReps;
		this.resultCount = vendorReps.size();
		super.addLink("vendor", "vendorservice/vendor", "application/json");
	}
}
