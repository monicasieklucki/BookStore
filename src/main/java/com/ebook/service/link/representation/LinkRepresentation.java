package com.ebook.service.link.representation;

import com.ebook.model.link.Link;

public abstract class LinkRepresentation {
//TODO this may not have gotten pushed. Dummy class to avoid errors for now
	private Link[] link;

	public Link[] getLink() {
		return link;
	}

	public void setLinks(Link... link) {
		this.link = link;
	}
	
}
