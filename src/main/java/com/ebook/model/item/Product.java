package com.ebook.model.item;

public class Product {
	private Integer id;
	private String title;
	private double price;
	
	public Product() {};
	
	public Product(String title, double price) {
		this.title = title;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
