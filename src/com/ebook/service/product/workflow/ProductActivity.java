package com.ebook.service.product.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebook.model.item.Product;
import com.ebook.model.item.ProductManager;
import com.ebook.model.link.Link;
import com.ebook.service.customer.representation.CustomerRepresentation;
import com.ebook.service.product.representation.ProductRepresentation;

public class ProductActivity {

	private static ProductManager prodService = new ProductManager();
	
	public ProductRepresentation getProduct(Integer id) {
		
		Product prod = prodService.getProductById(id);
				
		ProductRepresentation prodRep = new ProductRepresentation(prod);
		
		return prodRep;
	}
	
	public Set<ProductRepresentation> getProducts() {
		
		List<Product> products = prodService.getProducts();

		Set<ProductRepresentation> prodReps = new HashSet<ProductRepresentation>();
		for(Product prod : products) {
			prodReps.add(new ProductRepresentation(prod));
		}
		
		return prodReps;
	}
	
	@SuppressWarnings("unused")
	private void setLinks(ProductRepresentation prodRep) {
		// Set up the activities that can be performed on ocustomer
		Link GetProduct = new Link("List", "http://book-store-luc.com:8080/productservice/product?productid=" + prodRep.getId());
		Link GetProducts = new Link("List", "http://book-store-luc.com:8080/productservice/product");

		prodRep.setLinks(GetProduct, GetProducts);
	}
}
