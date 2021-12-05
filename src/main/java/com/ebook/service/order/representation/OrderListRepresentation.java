package com.ebook.service.order.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ebook.service.util.Representation;

@XmlRootElement(name="orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderListRepresentation extends Representation {
	@XmlElement(name="resultCount")
	public Integer resultCount;
	
	@XmlElementWrapper(name="data")
	@XmlElement(name="order")
	public List<OrderRepresentation> orders;
	
	public OrderListRepresentation() {}
	
	public OrderListRepresentation(List<OrderRepresentation> orders) {
		this.orders = orders;
		this.resultCount = orders.size();
	}
	
}
