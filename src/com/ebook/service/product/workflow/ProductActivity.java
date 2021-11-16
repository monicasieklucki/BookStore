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
		
		Link buy = new Link("availability", "http://localhost:8081/productservice/product/availability?productid=" + prod.getId(), "json");
		
		prodRep.setLinks(buy);
		
		return prodRep;
	}
	
	public Set<ProductRepresentation> getProducts() {
		
		List<Product> products = prodService.getProducts();

		Set<ProductRepresentation> prodReps = new HashSet<ProductRepresentation>();
		for(Product prod : products) {
			
			ProductRepresentation prodRep = new ProductRepresentation(prod);
						
			prodRep.setLinks();
			
			prodReps.add(prodRep);
		}
		
		
		
		return prodReps;
	}

}
