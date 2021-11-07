package com.ebook.service.order.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.customer.Customer;
import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation {
	private Integer orderId;
	private Customer customer;
	private List<OrderLine> orderLines;
	private boolean paymentReceived;
	private String orderState;
	
	public OrderRepresentation() {};
	
	public OrderRepresentation(Order order) {
		this.orderId = order.getOrderId();
		this.orderLines = order.getOrderLines();
		this.paymentReceived = order.isPaymentReceived();
		this.orderState = order.getOrderState();
		this.customer = order.getCustomer();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	
	

}
