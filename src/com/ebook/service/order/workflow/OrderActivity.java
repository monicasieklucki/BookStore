package com.ebook.service.order.workflow;

import com.ebook.model.order.Order;
import com.ebook.model.order.OrderManager;
import com.ebook.service.order.representation.OrderRepresentation;

public class OrderActivity {

	public OrderRepresentation getOrder(Integer orderId) {
		System.out.println("OrderActivity: Getting Order.....");
		Order order = OrderManager.getOrderById(orderId);
		return new OrderRepresentation(order);
	}

}
