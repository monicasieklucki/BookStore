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
import com.ebook.model.link.Link;


import com.ebook.model.item.Product;
import com.ebook.model.item.ProductManager;
import com.ebook.service.customer.representation.CustomerRepresentation;
import com.ebook.service.product.representation.ProductRepresentation;

public class ProductActivity {

	private static ProductManager prodService = new ProductManager();
	
	String hostname = "http://localhost:8080";
	//hostname = ""
	
	public ProductRepresentation getProduct(Integer id) {
		
		Product prod = prodService.getProductById(id);
				
		ProductRepresentation prodRep = new ProductRepresentation(prod);
		
		//BookStoreUri getAllProducts = new BookStoreUri("products", String.format("service/productservice/product/%d", prod.getId()),"application/json");
		// Add the links
		//Link buy = new Link("availability", "http://localhost:8081/productservice/product/availability?productid=" + prod.getId(), "json");

		Link GetProduct = new Link("GetProduct", "http://book-store-luc.herokuapp.com/productservice/product?productid=" + prodRep.getId(), "json");
		Link GetVendors = new Link("GetVendorWithProduct", "http://book-store-luc.herokuapp.com/vendorservice/vendor/product?productid=" + prodRep.getId(), "json");

		prodRep.setLinks(GetProduct, GetVendors);		
		
		return prodRep;
	}
	
	public Set<ProductRepresentation> getProducts() {
		
		List<Product> products = prodService.getProducts();

		Set<ProductRepresentation> prodReps = new HashSet<ProductRepresentation>();
		for(Product prod : products) {
			
			ProductRepresentation prodRep = new ProductRepresentation(prod);
			
			Link GetProduct = new Link("GetProduct", "http://book-store-luc.herokuapp.com/productservice/product?productid=" + prodRep.getId(), "json");
			Link GetVendors = new Link("GetVendors", "http://book-store-luc.herokuapp.com/vendorservice/vendor/product?productid=" + prodRep.getId(), "json");

			prodRep.setLinks(GetProduct, GetVendors);		
			//BookStoreUri getAllProducts = new BookStoreUri("products", String.format("service/productservice/product", prod.getId()),"application/json");
			
			prodReps.add(prodRep);
		}			
		return prodReps;
	}

}
