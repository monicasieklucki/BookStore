package com.ebook.model.customer;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.order.Order;
import com.ebook.model.review.Review;
import com.ebook.model.item.Product;

public class Customer {
	private String customerId;
	private String lastName;
	private String firstName;
	private Address billingAddress;
	private Address shippingAddress;
	
	private List<Order> orders = new ArrayList<Order>();

	private List<Review> reviews = new ArrayList<Review>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String id) {
		this.customerId = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void addReview(String customerId, Review review, String productId) {
		reviews.add(review);
	}
}
