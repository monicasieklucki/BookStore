package com.ebook.model.partner;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.partner.PartnerDetail;

public class Partner {
	
	private String partnerId;
	private String partnerName;
	private List<PartnerDetail> partnerDetails = new ArrayList<PartnerDetail>();
	
	public Partner() {}
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
		
	public List<PartnerDetail> getPartnerDetails() {
		return partnerDetails;
	}

	public void setPartnerDetails(List<PartnerDetail> partnerDetails) {
		this.partnerDetails = partnerDetails;
	}
	

}
