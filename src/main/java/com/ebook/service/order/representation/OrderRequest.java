package com.ebook.service.order.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.order.OrderLine;

@XmlRootElement(name = "OrderRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	private Integer customerId;
	private List<OrderLineRequest> orderLineReqs;
	
	public OrderRequest() {}
	
	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public List<OrderLineRequest> getOrderLineReqs() {
		return orderLineReqs;
	}


	public void setOrderLineReqs(List<OrderLineRequest> orderLineReqs) {
		this.orderLineReqs = orderLineReqs;
	}




}
