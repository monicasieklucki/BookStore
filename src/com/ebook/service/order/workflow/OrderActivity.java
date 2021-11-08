package com.ebook.service.order.workflow;

import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;
import com.ebook.model.order.OrderManager;
import com.ebook.service.order.representation.OrderRepresentation;
import com.ebook.service.order.representation.OrderRequest;

public class OrderActivity {

	public OrderRepresentation getOrder(Integer orderId) {
		System.out.println("OrderActivity: Getting Order.....");
		Order order = OrderManager.getOrderById(orderId);
		return new OrderRepresentation(order);
	}
	
	public OrderRepresentation addOrder(Integer customerId) {
		OrderRepresentation ordRep = new OrderRepresentation();
		Integer orderId = OrderManager.addOrder(customerId);
		ordRep.setCustomerId(customerId);
		ordRep.setOrderId(orderId);
		//Order will never be paid or updated status at creation
		ordRep.setPaymentReceived(false);
		ordRep.setOrderState("Open");
		
		return ordRep;	
	}
	
	//TODO would benefit from logic to check if product already exist to avoid duplicates. 
	//Also check on order status to make sure it can be updated. Maybe that should be managers job?
	public OrderRepresentation addProduct(Integer orderId, Integer productId, Integer quantity) {
		Order order = OrderManager.addProduct(orderId, productId, quantity);
		return new OrderRepresentation(order);
	}
	
	public String cancelOrder(Integer orderId) {
		OrderManager.updateOrderStatus(orderId, "Canceled");
		return "OK";
	}
	
	
	
	//Update Order
		//Payment an order status
		//Update orderline quantity
	
	//Get all products?
	
	//

}
