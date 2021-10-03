package test;

import com.ebook.model.order.Order;
import com.ebook.model.service.OrderService;

public class OrderServiceTest {

	public static void main(String[] args) {
		Integer orderId = OrderService.addOrder(1);
		OrderService.addProduct(orderId, 1, 2);
		OrderService.addProduct(orderId, 2, 1);
		System.out.println(OrderService.getOrderById(orderId));
		OrderService.updateOrderStatus(orderId, "Ordered");
		OrderService.updatePaymentStatus(orderId, true);
		OrderService.updateOrderStatus(orderId, "Shipped");
		OrderService.updateOrderStatus(orderId, "Delivered");
		

	}

}
