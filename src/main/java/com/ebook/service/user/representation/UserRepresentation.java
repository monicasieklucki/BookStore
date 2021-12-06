package com.ebook.service.user.representation;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.ebook.model.user.User;
import com.ebook.model.vendor.Vendor;
import com.ebook.model.vendor.VendorLine;
import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;
import com.ebook.service.vendor.representation.VendorLineRepresentation;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class UserRepresentation extends Representation {
	
	private Integer id;
	private String username;
	private String email;
	private String password;
	
	public UserRepresentation() { }
	
	public UserRepresentation(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
		
		// Links that should go on all representations of a vendor added here
		System.out.println("adding URIS");
		System.out.println(user.getUsername());
		BookStoreUri getUserLink = new BookStoreUri("get", String.format("service/userservice/user/%s", user.getUsername()), "application/json");
		BookStoreUri deleteUserLink = new BookStoreUri("delete", String.format("service/userservice/user"), "application/json");
		BookStoreUri updateUserLink = new BookStoreUri("update", String.format("service/userservice/user"),"application/json");
		super.addLinks(getUserLink, deleteUserLink, updateUserLink);
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void  setPassword(String password) {
		this.password = password;
	}
}
