package com.ebook.model.customer;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.order.Order;
import com.ebook.model.review.Review;
import com.ebook.model.item.Product;

public class Customer {
	private Integer customerId;
	private Integer billingid;
	private Integer shippingid;
	private String lastName;
	private String firstName;
	private Address billingaddress;
	private Address shippingaddress;
	
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
		return billingaddress;
	}

	public void setBillingAddress(Address billingaddress) {
		this.billingaddress = billingaddress;
	}

	public Address getShippingAddress() {
		return shippingaddress;
	}

	public void setShippingAddress(Address shippingaddress) {
		this.shippingaddress = shippingaddress;
	}
	
	public Integer getBillingAddressId() {
		return billingid;
	}

	public void setBillingAddressId(Integer id) {
		this.billingid = id;
	}
	
	public Integer getShippingAddressId() {
		return shippingid;
	}

	public void setShippingAddressId(Integer id) {
		this.shippingid = id;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer id) {
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
