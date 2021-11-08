package com.ebook.service.order.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.ebook.service.order.representation.OrderRepresentation;
import com.ebook.service.order.workflow.OrderActivity;

@Path("/orderservice/")
public class OrderResource {
	private static OrderActivity ordAct = new OrderActivity();

	@GET
	@Produces({ "application/xml", "application/json" })
	@Path("/order/{orderId}")
	public Response getOrder(@PathParam("orderId") Integer orderId) {
		OrderRepresentation ordRep = ordAct.getOrder(orderId);
		return Response.ok(ordRep).build();
	}

	@POST
	@Produces({ "application/xml", "application/json" })
	@Path("/order")
	public Response createOrder(@QueryParam("customerId") Integer customerId) {
		OrderRepresentation ordRep = ordAct.addOrder(customerId);
		return Response.ok(ordRep).build();
	}

	/**
	 * Adds a product to an order
	 * @param orderId ID of order to add product to
	 * @param productId ID of product to add
	 * @param quant quantity of product to add
	 * @return Response including the updated order
	 */
	@POST
	@Produces({ "application/xml", "application/json" })
	@Path("/order/{orderId}")
	public Response updateOrder(@PathParam("orderId") Integer orderId, @QueryParam("productId") Integer productId,
			@QueryParam("quantity") Integer quant) {	
		//TODO Potentially an issue where the order representation is duplicating the new orderLine
		OrderRepresentation ordRep = ordAct.addProduct(orderId, productId, quant);	
		return Response.ok(ordRep).build();
	}
	
	@PUT
	@Path("/order/{orderId}")
	public Response cancelOrder(@PathParam("orderId") Integer orderId, @QueryParam("cancelOrder") Boolean cancel) {
		if(cancel) {
			ordAct.cancelOrder(orderId);
		}
		return Response.ok().build();
	}

}
