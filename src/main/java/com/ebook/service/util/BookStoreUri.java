package com.ebook.service.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "link") //TODO this isn't coming through
public class BookStoreUri {
	private String baseUri;
	@XmlElement(name = "rel")
	private String rel;
	@XmlElement(name = "href")
	private String href;
	@XmlElement(name = "media-type")
	private String mediaType;
	
	//Set up the base url. This will be set up in heroku enviroments and if missing, local host is used.
	{
		if(System.getProperty("BASE_URI") != null) {
			baseUri = System.getProperty("BASE_URI");
		}else {
			baseUri = "http://localhost:8080/";
		}
	}
	
	public BookStoreUri() {}
	
	/**
	 * Creates a link that doesn't need a media type. For use in a delete action for example.
	 * @param name the rel value for the link
	 * @param href the URL for the link
	 */
	public BookStoreUri(String name, String href) {	
		this.rel = baseUri + name;
		this.href = baseUri + href;
	}
	
	/**
	 * Creates a link that needs a media type
	 * @param name rel value for the link
	 * @param href url value for the link
	 * @param mediaType media type of the link
	 */
	public BookStoreUri(String name, String href, String mediaType) {
		this(name, href);
		this.mediaType = mediaType;
	}
	
	public String getRel() {
		return this.rel;
	}

	public String getHref() {
		return href;
	}

	public String getMediaType() {
		return mediaType;
	}
	
	
}
