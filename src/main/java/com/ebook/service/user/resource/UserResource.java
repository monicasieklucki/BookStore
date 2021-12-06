package com.ebook.service.user.resource;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.CacheControl;


import com.ebook.service.user.workflow.*;
import com.ebook.service.user.representation.UserRequest;
import com.ebook.service.user.representation.UserRepresentation;
import com.ebook.model.user.User;

@Path("/userservice/")
public class UserResource {
	
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/user/{username}")
	public UserRepresentation getUser(@PathParam("username") String username) {
		System.out.println("GET METHOD Request from Client with userRequest String ............." + username);
		UserActivity userActivity = new UserActivity();
		return userActivity.getUser(username);
		
	}
	
	@POST
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/user")
	public UserRepresentation createUser(UserRequest userRequest) { 
		System.out.println("POST METHOD Request from Client with ............." + userRequest.getUsername());
		
		User user = new User();
		user.setId(userRequest.getId());
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		
		UserActivity userActivity = new UserActivity();
		return userActivity.createUser(user);
	}
	
	@DELETE
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/user/{user}")
	public Response deleteUser(UserRequest userRequest,@PathParam("user") User user) {
		System.out.println("Delete METHOD Request from Client with userRequest String ............." + userRequest.getUsername());

		UserActivity userActivity = new UserActivity();
		String res = userActivity.deleteUser(user);
		if (res.equals("OK")) {
			return Response.status(Status.OK).build();
		}
		return null;
	}
	
	@PUT
	@Produces({"application/xml" , "application/json"})
	@Consumes({"application/xml" , "application/json"})
	@Path("/user/{user}")
	public UserRepresentation updateEmployee(UserRequest userRequest, @PathParam("user") User user) { 
		System.out.println("POST METHOD Request from Client with ............." + userRequest.getUsername());
		
			
		UserActivity userActivity = new UserActivity();
		return userActivity.updateUser(user);
	}

}
