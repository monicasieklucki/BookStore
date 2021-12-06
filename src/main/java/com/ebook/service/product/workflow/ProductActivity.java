package com.ebook.service.product.workflow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriInfo;

import com.ebook.model.item.Product;
import com.ebook.model.item.ProductManager;
import com.ebook.service.customer.representation.CustomerRepresentation;
import com.ebook.service.product.representation.ProductRepresentation;

public class ProductActivity {

	private static ProductManager prodService = new ProductManager();
	
	public ProductRepresentation getProduct(Integer id) {
		
		Product prod = prodService.getProductById(id);
				
		ProductRepresentation prodRep = new ProductRepresentation(prod);
		
		//BookStoreUri getAllProducts = new BookStoreUri("products", String.format("service/productservice/product/%d", prod.getId()),"application/json");
		
		return prodRep;
	}
	
	public Set<ProductRepresentation> getProducts() {
		
		List<Product> products = prodService.getProducts();

		Set<ProductRepresentation> prodReps = new HashSet<ProductRepresentation>();
		for(Product prod : products) {
			
			ProductRepresentation prodRep = new ProductRepresentation(prod);
						
			//BookStoreUri getAllProducts = new BookStoreUri("products", String.format("service/productservice/product", prod.getId()),"application/json");
			
			prodReps.add(prodRep);
		}			
		return prodReps;
	}

}
