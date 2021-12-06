package com.ebook.service.order.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.ebook.service.customer.workflow.CustomerActivity;
import com.ebook.service.order.representation.OrderListRepresentation;
import com.ebook.service.order.representation.OrderRepresentation;
import com.ebook.service.order.representation.OrderRequest;
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
	
	@GET
	@Produces({"application/json", "application/xml"})
	@Path("/order")
	public Response getOrders(@DefaultValue("0") @QueryParam("vendorId") Integer vendorId, @DefaultValue("0") @QueryParam("customerId") Integer custId,
			@DefaultValue("") @QueryParam("statuses") String statuses){
			OrderListRepresentation orderReps = ordAct.getOrders(vendorId, custId, statuses);
			return Response.ok(orderReps).build();
		}

	/**
	 * Post request with no order id created will first create a new order. 
	 * Orderlines can be added at the same time as the order is created. 
	 * No checks to see if customer has an existing open order.
	 * @param ordReq order request contains the customerID and any orderlines for the new order
	 * @return Order Representation for the order created. 
	 */
	@POST
	@Produces({ "application/xml", "application/json" })
	@Consumes({"application/json", "application/xml"})
	@Path("/order")
	public Response createOrder(OrderRequest ordReq) {
		Integer orderId = ordAct.addOrder(ordReq.getCustomerId()).getOrderId();
		OrderRepresentation ordRep = ordAct.updateProduts(orderId, ordReq);
		return Response.ok(ordRep).build();
	}
	
	@PUT
	@Produces({"application/json", "application/xml"})
	@Consumes({"application/json", "application/xml"})
	@Path("/order/{orderId}")
	public Response updateOrderLines(@PathParam("orderId") Integer orderId, OrderRequest ordReq) {
		String currentStatus = ordAct.getOrder(orderId).getOrderState();
		if( currentStatus.equalsIgnoreCase("open")){
			return Response.accepted(ordAct.updateProduts(orderId, ordReq)).build();
		}
		return Response.status(409, "Invalid request for order, check status").build();
	}
	
	@PUT
	@Path("/order/{orderId}/status/{status}")
	public Response statusUpdate(@PathParam("orderId") Integer orderId, @PathParam("status") String status) {
		String currentStatus = ordAct.getOrder(orderId).getOrderState();
		if(currentStatus.equals("Ordered") && status.equalsIgnoreCase("SHIPPED") ) {
			OrderRepresentation orderRep = ordAct.shipOrder(orderId);
			return Response.accepted(orderRep).build();
		} else if(currentStatus.equals("Shipped") && status.equalsIgnoreCase("DELIVERED")) {
			OrderRepresentation orderRep = ordAct.deliverOrder(orderId);
			return Response.accepted(orderRep).build();
		} else {
			return Response.status(409, "Invalid request for order status").build();
		}
	}

	@POST
	@Path("/order/{orderId}/payment")
	@Consumes({"application/json", "application/xml"})
	public Response takePayment(@PathParam("orderId") Integer orderId) {
		String currentStatus = ordAct.getOrder(orderId).getOrderState();
		if (currentStatus.equals("Open")) {
			OrderRepresentation orderRep = ordAct.placeOrder(orderId);		
			return Response.accepted(orderRep).build();
		} else {
			return Response.status(409, "Invalid request for order status").build();
		}
	}
		

	
	@DELETE
	@Path("order/{orderId}")
	public Response cancelOrder(@PathParam("orderId") Integer orderId) {
		String currentStatus = ordAct.getOrder(orderId).getOrderState();
		if(currentStatus.equals("Open") || currentStatus.equals("Ordered")) {
			ordAct.cancelOrder(orderId);
			return Response.ok().build();
		} else {
			return Response.status(409,"Invalid request, check order status.").build();
		}	
	}

}
