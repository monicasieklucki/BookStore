package com.ebook.service.order.representation;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.customer.Customer;
import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;
import com.ebook.service.util.Representation;

@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class OrderRepresentation extends Representation {
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
		switch (this.orderState) {
		case "open":
			super.addLink("order/cancel", String.format("service/orderservice/order/%d", orderId), "application/json");
			super.addLink("orderlines", String.format("service/orderservice/order/%d",orderId), "appliation/json");
			if(this.orderLines.size() > 0) {
				super.addLink("order/payment", String.format("service/orderservice/order/%d/payment",orderId, "application/json"));
			}
			break;
		case "ordered":
			super.addLink("order/cancel", String.format("service/orderservice/order/%d", orderId), "application/json");
			super.addLink("orderlines", String.format("service/orderservice/order/%d",orderId), "appliation/json");
			super.addLink("self", "service/orderservice/order/" + orderId, "application/json");
			super.addLink("order/ship", String.format("/service/orderservice/order/%d/status/delivered", orderId), "application/json");			
		case "shipped":
			super.addLink("order/deliver", String.format("/service/orderservice/order/%d/status/delivered", orderId), "application/json");
		}
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
}
