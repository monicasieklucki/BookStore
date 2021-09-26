package com.ebook.model.review;

import java.util.List;
import java.util.ArrayList;

import com.ebook.model.item.Product;


public class Review {
	
	private String reviewId;
	private String review;
	
	private List<Review> reviews = new ArrayList<Review>();
	
	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	
	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
