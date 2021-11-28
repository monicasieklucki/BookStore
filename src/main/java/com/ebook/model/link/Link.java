package com.ebook.model.link;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Link")
public class Link {
	private String rel;
	private String url;
	private String mediaType;
	
	public Link() {}
	
	public Link(String rel, String url, String mediaType) {
		this.rel = rel;
		this.url = url;
		this.mediaType = mediaType;
	}
	
	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}