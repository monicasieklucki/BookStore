package test;

import com.ebook.model.order.Order;
import com.ebook.model.order.OrderManager;

public class OrderServiceTest {

	public static void main(String[] args) {
		Integer orderId = OrderManager.addOrder(1);
		OrderManager.addProduct(orderId, 1, 2);
		OrderManager.addProduct(orderId, 2, 1);
		System.out.println(OrderManager.getOrderById(orderId));
		OrderManager.updateOrderStatus(orderId, "Ordered");
		OrderManager.updatePaymentStatus(orderId, true);
		OrderManager.updateOrderStatus(orderId, "Shipped");
		OrderManager.updateOrderStatus(orderId, "Delivered");
		

	}

}
