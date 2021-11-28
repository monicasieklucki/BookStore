package com.ebook.service.order.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.order.OrderLine;

@XmlRootElement(name = "OrderRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRequest {
	private Integer customerId;
	private List<OrderLine> orderLine;
	
	
	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public List<OrderLine> getOrderLine() {
		return orderLine;
	}


	public void setOrderLine(List<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}

}
