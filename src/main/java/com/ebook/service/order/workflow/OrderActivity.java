package com.ebook.service.order.workflow;

import java.util.ArrayList;
import java.util.List;

import com.ebook.model.customer.CustomerManager;
import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;
import com.ebook.model.order.OrderManager;
import com.ebook.service.order.representation.OrderLineRequest;
import com.ebook.service.order.representation.OrderListRepresentation;
import com.ebook.service.order.representation.OrderRepresentation;
import com.ebook.service.order.representation.OrderRequest;

public class OrderActivity {

	/**
	 * Returns an order representation for the given Id. 
	 * Links for next steps based on the status and determined by the status of the order. 
	 * @param orderId Id of the order to retrieve. 
	 * @return OrderRepresentation for the given ID
	 */
	public OrderRepresentation getOrder(Integer orderId) {
		OrderRepresentation orderRep = new OrderRepresentation(OrderManager.getOrderById(orderId));	
		return orderRep;
	}
	
	public OrderListRepresentation getOrders(Integer vendorId, Integer customerId, String statuses) {
		List<Order> orders = OrderManager.getOrders(vendorId, customerId, statuses);
		List<OrderRepresentation> orderReps = new ArrayList<>();
		for(Order o : orders) {
			orderReps.add(new OrderRepresentation(o));
		}
		return new OrderListRepresentation(orderReps);
	}
	
	/**
	 * Creates a new order. 
	 * This step must always be done before adding items. 
	 * @param customerId customer placing the order
	 * @return OrderRepresentation after creation. 
	 */
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
		
	/**
	 * Removes the order.
	 * @param orderId Id to delete
	 * @return OK status. 
	 */
	public String cancelOrder(Integer orderId) {
		OrderManager.updateOrderStatus(orderId, "Canceled");
		return "OK";
	}
	
	/**
	 * Updates order status to Ordered and payment status to true.
	 * Checks for valid status done by resource class.
	 * @param orderId Id of order being placed
	 * @return Representation of order after update
	 */
	public OrderRepresentation placeOrder(Integer orderId) {
		OrderManager.updatePaymentStatus(orderId, true);
		OrderManager.updateOrderStatus(orderId, "Ordered");
		OrderRepresentation orderRep = new OrderRepresentation(OrderManager.getOrderById(orderId));
		return orderRep;
	}
	
	/**
	 * Updates order status to shipped. 
	 * Checks for validity of order status being done by the resource class
	 * @param orderId Id of order being updated
	 * @return Representation of the order after the update. 
	 */
	public OrderRepresentation shipOrder(Integer orderId) {
		OrderManager.updateOrderStatus(orderId, "Shipped");
		OrderRepresentation orderRep = new OrderRepresentation(OrderManager.getOrderById(orderId));
		return orderRep;
	}
	
	/**
	 * Updated order status to Delivered. 
	 * Checks for validity of action being done by the resource class.
	 * @param orderId Id of order to mark delivered
	 * @return OrderRepresentation for the order after updates made. 
	 */
	public OrderRepresentation deliverOrder(Integer orderId) {
		OrderManager.updateOrderStatus(orderId, "Delivered");
		return new OrderRepresentation(OrderManager.getOrderById(orderId));
	}

	/**
	 * Take an order request for a given Id. Updates products already on the order. 
	 * Adds products that do not currently exist on the order. 
	 * Order model will remove any orderLine with a quantity of 0
	 * @param orderId Id of order to update.
	 * @param ordReq OrderRequest. Contains a customer id and list of orderLines to be updated or added
	 * @return Order representation after updates are all made
	 */
	public OrderRepresentation updateProduts(Integer orderId, OrderRequest ordReq) {		
		Order order = OrderManager.getOrderById(orderId);
		List<Integer> currentProducts = new ArrayList<>();
		for(OrderLine ol : order.getOrderLines()) {
			currentProducts.add(ol.getProduct().getId());
		}
		for(OrderLineRequest ol : ordReq.getOrderLineReqs()) {
			if(currentProducts.contains(ol.getProductId())) {
				OrderManager.updateOrderLine(orderId, ol.getProductId(), ol.getQuantity());
			} else {
				OrderManager.addProduct(orderId, ol.getProductId(), ol.getQuantity());
			}
		}
		return new OrderRepresentation(OrderManager.getOrderById(orderId));
	}

}
