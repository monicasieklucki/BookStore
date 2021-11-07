package com.ebook.service.order.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ebook.service.order.representation.OrderRepresentation;
import com.ebook.service.order.workflow.OrderActivity;

@Path("/orderservice/")
public class OrderResource {
	private static OrderActivity ordAct = new OrderActivity();
	
	@GET
	@Produces({"application/xml" , "application/json"})
	@Path("/order/{orderId}")
	public Response getOrder(@PathParam("orderId") Integer orderId) {
		OrderRepresentation ordRep = ordAct.getOrder(orderId);
		return Response.ok(ordRep).build();
	}

}
