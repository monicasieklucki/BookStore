package com.ebook.model.order;

import com.ebook.dal.CustomerDAO;
import com.ebook.dal.OrderDAO;
import com.ebook.dal.ProductDAO;
import com.ebook.model.customer.Customer;
import com.ebook.model.item.Product;

public class OrderManager {
	private static OrderDAO ordDAO = new OrderDAO();
	private static ProductDAO prodDAO = new ProductDAO();
	private static CustomerDAO custDAO = new CustomerDAO();
	
	/**
	 * Creates a new order for the given customer
	 * @param customerId the id of the customer creating the order
	 * @return the new order that was created. 
	 */
	public static Integer addOrder(Integer customerId) {
		Customer cust = custDAO.getCustomer(customerId);
		Order ord = new Order(cust);
		try {
		ordDAO.addOrder(ord);
		} catch (Exception se ) {
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		return ord.getOrderId();
	}
	
	/**
	 * Adds a product to the order with a specific quantity. 
	 * @param orderId Id of the order to be updated
	 * @param productId Id of the product to add
	 * @param quantity quantity of the product added. 
	 * @return returns an order object with the updated orderLines
	 */
	public static Order addProduct(Integer orderId, Integer productId, Integer quantity) {
		Order ord = null;
		try {	
			Product product = prodDAO.getProduct(productId); 
			ord = ordDAO.getOrder(orderId);
			OrderLine ol = ord.addProduct(product, quantity);
			ordDAO.addOrderLine(ord, ol);
			
		} catch (Exception se) {
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		return ord;
	}
	
	/**
	 * gets the order for the given id.
	 * @param orderId order id to retrieve. 
	 * @return order for the given ID.
	 */
	public static Order getOrderById(Integer orderId) {
		Order order = null;
		try {
			order = ordDAO.getOrder(orderId);
		} catch (Exception se) {
			System.err.println(se.getMessage());
		}
		return order;
	}
	
	/**
	 * Update the payment status to either true or false
	 * @param orderId order ID to update
	 * @param status true if payment was received.
	 */
	public static void updatePaymentStatus(Integer orderId, boolean status) {
		try {
			Order ord = ordDAO.getOrder(orderId);
			ord.setPaymentReceived(status);
			ordDAO.updateOrder(ord);
		} catch(Exception se) {
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	/**
	 * Updates the order with the given status
	 * @param orderId orderId to update
	 * @param orderState possible values Ordered, Shipped, Delivered, Canceled
	 */
	public static void updateOrderStatus(Integer orderId, String orderState) {
		try {
			Order ord = ordDAO.getOrder(orderId);
			switch (orderState.toUpperCase()) {
			case "ORDERED" :
				ord.confirmOrder();
				break;
			case "SHIPPED" :
				ord.orderSendOut();
				break;
			
			case "DELIVERED" :
				ord.orderDelivered();
				break;
				
			case "CANCELED" :
				ord.cancelOrder();
				break;
			default :
				throw new Exception("Order state invalid");	
			}
			ordDAO.updateOrder(ord);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Updates the quantity of a product on the order. If quantity is 0, product is removed.
	 * @param orderId
	 * @param productId
	 * @param quantity
	 */
	public static void updateOrderLine(Integer orderId, Integer productId, Integer quantity) {
		try {
			Order ord = ordDAO.getOrder(orderId);
			Product product = prodDAO.getProduct(productId);
			ord.updateProduct(product, quantity);
			ordDAO.updateOrder(ord);
		} catch(Exception e) {
			System.err.print(e.getMessage());
			e.printStackTrace();
		}
	}
		
}
