package com.ebook.server;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class EbookServer {

	public static void main(String[] args) {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(com.ebook.service.vendor.resource.VendorResource.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new com.ebook.service.vendor.resource.VendorResource()));
        factoryBean.setAddress("http://localhost:8081/");
        Server server = factoryBean.create();

        System.out.println("HR Employee System Restful Server ready..............................."); 

	}

}
