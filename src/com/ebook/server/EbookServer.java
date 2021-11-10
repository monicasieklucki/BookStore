package com.ebook.server;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class EbookServer {

	public static void main(String[] args) {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(com.ebook.service.customer.resource.CustomerResource.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new com.ebook.service.customer.resource.CustomerResource()));
        factoryBean.setResourceClasses(com.ebook.service.vendor.resource.VendorResource.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new com.ebook.service.vendor.resource.VendorResource()));
        factoryBean.setResourceClasses(com.ebook.service.order.resource.OrderResource.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new com.ebook.service.order.resource.OrderResource()));
        factoryBean.setResourceClasses(com.ebook.service.product.resource.ProductResource.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new com.ebook.service.product.resource.ProductResource()));
        factoryBean.setAddress("http://localhost:8081/");
        //http://book-store-luc.herokuapp.com/
        //http://localhost:8081/
        Server server = factoryBean.create();

        System.out.println("HR Employee System Restful Server ready..............................."); 

	}

}
