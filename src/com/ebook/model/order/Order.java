package com.ebook.model.order;

import java.util.ArrayList;
import java.util.List;
import com.ebook.model.item.Product;

public class Order {
	private String orderId;
	private List<OrderLine> OrderLines = new ArrayList<OrderLine>();
	private boolean paymentReceived;
	private String orderState = "Open";
	
	public Order() {}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderLine> getOrderLines() {
		return OrderLines;
	}

	public void setOrderLines(List<OrderLine> OrderLines) {
		this.OrderLines = OrderLines;
	}

	
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getOrderState() {
		return orderState;
	}
	
	public boolean isPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}
	
	public void addProduct(Product product, int quantity) {
		if (orderState.equals("Open")) {
			OrderLines.add(new OrderLine(product, quantity));
		} else {
			throw new IllegalStateException("Can only add product in Open state.");
		}
	}
	
	public void cancelOrder() {
		if (orderState.equals("Open") || orderState.equals("Ordered")) {
			orderState = "Canceled";
		} else {
			throw new IllegalStateException("Cannot cancel order in this state.");
		}
	}
	
	public void confirmOrder() {
		if (getOrderLines().isEmpty()) {
			orderState = "Canceled";
		} else if (orderState.equals("Open")) {
			orderState = "Ordered";
		} else {
			throw new IllegalStateException("Cannot confirm order in this state.");
		}
	}
	
	public void orderDelivered() {
		if (orderState.equals("Shipped")) {
			orderState = "Delivered";
		} else {
			throw new IllegalStateException("Cannot be delivered from in this state.");
		}
	}
	
	public void orderPayed() {
		if (orderState.equals("Ordered")) {
			setPaymentReceived(true);
		} else {
			throw new IllegalStateException("Cannot pay in this state.");
		}
	}
	
	public void orderSendOut() {
		if (orderState.equals("Ordered") && paymentReceived) {
			orderState = "Shipped";
		} else {
			throw new IllegalStateException("Cannot send out in this state.");
		}
	}
	
	public boolean isFinished() {
		if (orderState.equals("Delivered") || orderState.equals("Canceled")) {
			return true;
		}
		return false;
	}
	
	public double getOrderTotal() {
		double total = 0.00;
		for (OrderLine line : OrderLines) {
			total += line.getProduct().getPrice() * line.getQuantity();
		}
		return total;
	}

}
