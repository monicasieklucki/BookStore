package com.ebook.service.order.representation;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.customer.Customer;
import com.ebook.model.link.Link;
import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {
	private Integer orderId;
	private Integer customerId; //will have user make calls for customer details through customerservice
	private List<OrderLine> orderLines;
	private boolean paymentReceived;
	private String orderState;
	
	public OrderRepresentation() {};
	
	public OrderRepresentation(Order order) {
		this.orderId = order.getOrderId();
		this.orderLines = order.getOrderLines();
		this.paymentReceived = order.isPaymentReceived();
		this.orderState = order.getOrderState();
		this.customerId = order.getCustomer().getCustomerId();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customer) {
		this.customerId = customer;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public boolean isPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	protected List<Link> links;
	
	public List<Link> getLinks() {
		return links;
	}
	
	public void setLinks(Link...links) {
		this.links = Arrays.asList(links);
	}
	
	

}
