package com.ebook.service.product.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebook.model.item.Product;
import com.ebook.model.item.ProductManager;
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
}
