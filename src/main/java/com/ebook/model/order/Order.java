package com.ebook.model.order;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.customer.Customer;
import com.ebook.model.item.Product;

public class Order {
	private Integer orderId;
	private Customer customer;
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	private boolean paymentReceived = false;
	private String orderState = "Open";
	
	public Order() {};
	public Order(Customer customer) {
		this.customer = customer;
	};
	
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	};
	
	public Customer getCustomer() {
		return customer;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> OrderLines) {
		this.orderLines = OrderLines;
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
	
	public OrderLine addProduct(Product product, int quantity) {
		OrderLine ol = null;
		if (orderState.equals("Open")) {
			ol = new OrderLine(product, quantity);
			orderLines.add(ol);
		} else {
			throw new IllegalStateException("Can only add product in Open state.");
		}
		return ol;
	}
	
	public void updateProduct(Product product, int quantity) {
		for(OrderLine ol : orderLines ) {
			if (ol.getProduct().getId() == product.getId()) {
				if (quantity == 0) {
					orderLines.remove(ol);
				} else {
					ol.setQuantity(quantity);
				}
			} else {
				throw new IllegalStateException("Product not found in order, can't update product.");
			}
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
		for (OrderLine line : orderLines) {
			total += line.getProduct().getPrice() * line.getQuantity();
		}
		return total;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\n*****ORDER DETAILS*****");
		sb.append(String.format("\nOrder ID: \t\t%d\n OrderState: \t\t%s\n Payment Status: \t%s\n", orderId, orderState, paymentReceived));
		sb.append(String.format("Customer ID:\t\t%d \nCustomer Name: \t%s %s\n", customer.getCustomerId(), customer.getFirstName(), customer.getLastName()));
		for(OrderLine ol : orderLines) {
			Product product = ol.getProduct();
			sb.append(String.format("\tProduct ID: \t%d\n\tProduct Title: \t%s\n\tProduct Price: \t$%.2f\n", product.getId(), product.getTitle(), product.getPrice()));
			sb.append(String.format("\tProduct Quantity: %d\n", ol.getQuantity()));
		}
		sb.append(String.format("Order Total: $%.2f", getOrderTotal()));
		return sb.toString();
	}

}
