package com.ebook.service.user.workflow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ebook.model.link.Link;
import com.ebook.model.user.User;
import com.ebook.model.user.UserManager;
import com.ebook.service.user.representation.UserRepresentation;
import com.ebook.dal.UserDAO;

import com.ebook.service.util.BookStoreUri;
import com.ebook.service.util.Representation;

public class UserActivity {
	
	private static UserManager userService = new UserManager();
	
	public UserRepresentation getUser(String username) {
		
		User user = userService.GetUser(username);
		
		
		UserRepresentation userRep = new UserRepresentation(user);
		
		return userRep;
	}
	
	public UserRepresentation createUser(User user) {
		
		userService.addUser(user);
		
		UserRepresentation userRep = new UserRepresentation(user);
		
		Link CreateCustomer = new Link("CreateCustomer", "http://book-store-luc.herokuapp.com/service/customerservice/customer/user", "json");
		Link GetUser = new Link("GetUser", "http://book-store-luc.herokuapp.com/service/userservice/user/" + userRep.getUsername(), "json");
		//Link GetCustomer = new Link("GetCustomer", "","json")
		userRep.setLinks(CreateCustomer, GetUser);	
		
		return userRep;
	}
	
	public String deleteUser(User user) {
		
		userService.deleteUser(user);
		
		return "OK";
	}
	
	public UserRepresentation updateUser(User user) {
		
		userService.updateCustomer(user);
		
		UserRepresentation userRep = new UserRepresentation(user);

		return userRep;
	}

}
