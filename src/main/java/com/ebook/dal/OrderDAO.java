package com.ebook.dal;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;
import com.ebook.model.item.Product;

public class OrderDAO {
	private ProductDAO prodDAO = new ProductDAO();
	private CustomerDAO custDAO = new CustomerDAO();
	
	/**
	 * Gets the order for the given ID including updating the OrderLines with all products on the order
	 * @param orderId order id to retrieve.
	 * @return order An order with all details. 
	 */
	public Order getOrder(Integer orderId) {
		String selectOrderQuery = "SELECT customerid, orderId, orderState, paymentrec FROM Orders WHERE orderID = " + orderId + ";";
	    try (Connection con = DBHelper.getConnection();
	    		Statement st = con.createStatement();) {		
	    	ResultSet orderRS = st.executeQuery(selectOrderQuery);      
	    	System.out.println("orderDAO: *************** Query " + selectOrderQuery);
	    	      
    	  Order order = new Order();
	      while ( orderRS.next() ) {
	    	  order.setOrderId(orderRS.getInt("orderID"));
	    	  order.setCustomer(custDAO.getCustomer(orderRS.getInt("customerid")));
	    	  order.setOrderState(orderRS.getString("orderstate"));
	    	  order.setPaymentReceived(orderRS.getBoolean("paymentrec"));

	      }
	      //close to manage resources
	      orderRS.close();
	      getAllOrderLines(order);
	      	    		        
	      return order;
	    }	    
	    catch (SQLException se) {
	      System.err.println("orderDAO: Threw a SQLException retrieving the order object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }
	
	public List<Order> getOrders (Integer vendorId, Integer customerId, String statuses){
		String select = "SELECT o.orderid, o.customerid, o.orderstate, o.paymentrec, ol.productid, ol.quantity, p.title, p.price "
				+ "FROM orders o JOIN orderline ol ON o.orderid = ol.orderid "
				+ "JOIN product p ON ol.productid = p.id " 
				+ "JOIN vendorline vl ON vl.productid = ol.productid ";
		if(vendorId > 0) {
			select += "WHERE vl.vendorId = ? ";	
		}else {
			select += "WHERE vl.vendorId != ? ";
		}
		if(customerId > 0) {
			select += "AND o.customerid = ? ";
		}else {
			select += "AND o.customerid != ? ";
		}
		if(!statuses.isEmpty()) {
			select += String.format("AND o.orderstate ilike any('{ %s }'::text[])", statuses);
		}
		select += "ORDER BY o.orderid;";
		System.out.println(select);
		try (Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(select);){
			statement.setInt(1, vendorId);
			statement.setInt(2, customerId);
			ResultSet ordersRs = statement.executeQuery();
			List<Order> orders = new ArrayList<>();
			Integer currentOrder = 0;
			while(ordersRs.next()) {
				Integer orderId = ordersRs.getInt("orderid");
				if(currentOrder != orderId) {
					Order order = new Order(custDAO.getCustomer(ordersRs.getInt("customerid")));
					order.setOrderId(orderId);
					order.setOrderState(ordersRs.getString("orderstate"));
					order.setPaymentReceived(ordersRs.getBoolean("paymentrec"));
					Product product = new Product(ordersRs.getString("title"), ordersRs.getDouble("price"));
					product.setId(ordersRs.getInt("productid"));
					List<OrderLine> orderLines = new ArrayList<OrderLine>();
					orderLines.add(new OrderLine(product, ordersRs.getInt("quantity")));
					order.setOrderLines(orderLines);
					currentOrder = orderId;
					orders.add(order);
				}else {
					Product product = new Product(ordersRs.getString("title"), ordersRs.getDouble("price"));
					product.setId(ordersRs.getInt("productid"));
					orders.get(orders.size() - 1).getOrderLines().add(new OrderLine(product, ordersRs.getInt("quantity")));
				}
			}
			ordersRs.close();
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Adds an order to the DB. Does not add any products if OrderLines exist.
	 * The order should be created before products start being added to the order.
	 * @param order order object to add.
	 */
	public void addOrder(Order order) {
		String insertStm = "INSERT INTO ORDERS (customerid, paymentRec, orderState) VALUES(?, ?, ?);";
		try (Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(insertStm, Statement.RETURN_GENERATED_KEYS);) {
			statement.setInt(1, order.getCustomer().getCustomerId());
			statement.setBoolean(2, order.isPaymentReceived());
			statement.setString(3, order.getOrderState());

			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating Order failed, no rows affected.");
			}

			try (ResultSet generatedId = statement.getGeneratedKeys()) {
				if (generatedId.next()) {
					order.setOrderId(generatedId.getInt(1));
				} else {
					throw new SQLException("Creating Order failed, no ID obtained.");
				}
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
	}
	
	/**
	 * Add a product/orderline to the order
	 * @param order order to update
	 * @param ol orderline to add
	 */
	public void addOrderLine(Order order, OrderLine ol) {
		//TODO possibly need to handle a situation where the product is already on the order. 
		// Could also look into making a composite primary key on orderline and let DB handle this logic?
		String insertStm = "INSERT INTO orderline VALUES(?, ?, ?);";
		try (Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(insertStm);
				){
			statement.setInt(1, order.getOrderId());
			statement.setInt(2, ol.getProduct().getId());
			statement.setInt(3, ol.getQuantity());
			
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating orderline failed, no rows affected.");
			}
			order.addProduct(ol.getProduct(), ol.getQuantity());
		}catch (SQLException se) {
			System.err.println(se.getMessage());
			se.printStackTrace();		
		}
	}
	
	/**
	 * gets all of the products on the order and their quantities
	 * @param order the order to look up
	 * @return a list of OrderLines. Each orderline represents one product on the order.
	 */
	public List<OrderLine> getAllOrderLines(Order order){
		String queryStm = "SELECT productId, quantity FROM orderline where orderid = ?;";
		try(Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(queryStm)
			){
			statement.setInt(1, order.getOrderId());
			ResultSet rs  = statement.executeQuery();

			List<OrderLine> orderLines = new ArrayList<>();
			while(rs.next()) {
				OrderLine ol = new OrderLine();
				ol.setProduct(prodDAO.getProduct(rs.getInt(1)));
				ol.setQuantity(rs.getInt(2));
				orderLines.add(ol);
			}
			order.setOrderLines(orderLines);
			rs.close();
			return orderLines;
		}catch(SQLException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Updates the order status and payment status for the given order object
	 * @param order the order to update
	 */
	public void updateOrder(Order order) {
		String updateStm = "UPDATE orders SET orderstate = ? ,  paymentrec = ? where orderid = ?;";
		try (Connection con = DBHelper.getConnection();
				PreparedStatement statement = con.prepareStatement(updateStm);
			){
			statement.setString(1, order.getOrderState());
			statement.setBoolean(2, order.isPaymentReceived());
			statement.setInt(3, order.getOrderId());
			System.out.println(statement);
			
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Failed to update order status. No rows affected");
			}
		} catch(SQLException se) {
			System.err.println(se.getMessage());
			System.err.printf("OrderDAO ERROR: Could not update order with orderID: %d\n",order.getOrderId());
		}
		
	}
}
