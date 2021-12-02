package com.ebook.service.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

public abstract class Representation {
	
	@XmlElementWrapper(name = "links")
	@XmlElement(name="link")
	public List<BookStoreUri> links = new ArrayList<BookStoreUri>();
	
	public Representation() {}
	
	public void addLink(String name, String href) {
		links.add(new BookStoreUri(name, href));
	}
	
	public void addLink(String name, String href, String mediaType) {
		links.add(new BookStoreUri(name, href, mediaType));
	}
	
	public List<BookStoreUri> getLinks(){
		return links;
	}
	
	public void addLinks(BookStoreUri...bookStoreUris) {
		links.addAll(Arrays.asList(bookStoreUris));
	}
}
