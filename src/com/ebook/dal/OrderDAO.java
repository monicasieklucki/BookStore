package com.ebook.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.model.order.Order;
import com.ebook.model.order.OrderLine;
import com.ebook.model.item.Product;

public class OrderDAO {
	
	public Order getOrder(String orderId) {
	 	 
	    try { 		
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String selectOrderQuery = "SELECT orderId, orderState, paymentReceived FROM Order WHERE orderID = '" + orderId + "'";

	    	ResultSet orderRS = st.executeQuery(selectOrderQuery);      
	    	System.out.println("orderDAO: *************** Query " + selectOrderQuery);
	    	
	      //Get vendor
    	  Order order = new Order();
	      while ( orderRS.next() ) {
	    	  order.setOrderId(orderRS.getString("orderID"));
	    	  order.setOrderState(orderRS.getString("orderState"));
	    	  order.setPaymentReceived(orderRS.getBoolean("isPaymentReceived"));

	      }
	      //close to manage resources
	      orderRS.close();
	      	    		  
	      //Get vendor details
	      String selectOrderDetailQuery = "SELECT orderID, productId, productTitle, productPrice, quantity, FROM VendorLine WHERE vendorID = '" + orderId + "'";
	      ResultSet ordRS = st.executeQuery(selectOrderDetailQuery);
	      
	      List<OrderLine> orderLines = new ArrayList<OrderLine>();
	      
    	  System.out.println("orderDetailDAO: *************** Query " + selectOrderDetailQuery);
    	  
	      while ( ordRS.next() ) {
		      OrderLine orderLine = new OrderLine();
		      Product product = new Product();
		      
	    	  orderLine.setQuantity(ordRS.getInt("quantity"));
	    	  
	    	  product.setId(ordRS.getInt("id"));
	    	  product.setTitle(ordRS.getString("title"));
	    	  product.setPrice(ordRS.getDouble("price"));
	    	  
	    	  orderLine.setProduct(product);
	    	  
	    	  orderLines.add(orderLine);
	      }
	      
	      order.setOrderLines(orderLines);
	      //close to manage resources
	      ordRS.close();
	      st.close();
	      
	      return order;
	    }	    
	    catch (SQLException se) {
	      System.err.println("orderDAO: Threw a SQLException retrieving the order object.");
	      System.err.println(se.getMessage());
	      se.printStackTrace();
	    }
	    
	    return null;
	  }

		
	public void addOrder(Order order) {
	    try { 		
	    	//Get vendor
	    	Statement st = DBHelper.getConnection().createStatement();
	    	String insertOrderQuery = "INSERT INTO Order (paymentReceived, orderState) VALUES (" + order.isPaymentReceived() + ", " + order.getOrderState() + ")";
	    	
	    	st.executeUpdate(insertOrderQuery);      
	    	System.out.println("orderDAO: *************** Query " + insertOrderQuery);
	    			    
		    
		    for (int i = 0; i < order.getOrderLines().size();i++) {
		    	OrderLine orderLine = order.getOrderLines().get(i);
		    	String insertVendorDetailsQuery = "INSERT INTO orderLine (orderId, productId, productTitle, productPrice, quantity) VALUES (" + order.getOrderId()+ ", " + orderLine.getProduct().getId() + ", " + orderLine.getProduct().getTitle() + ", " + orderLine.getProduct().getPrice() + ", " + orderLine.getQuantity() + ");";
		    	
		    	st.executeUpdate(insertVendorDetailsQuery);  
		    	
		    	
		    }
		    st.close();  
		   	      
	    }	    
	    catch (SQLException se) {
	        System.err.println("orderDAO: Threw a SQLException inserting the vorder object.");
	        System.err.println(se.getMessage());
	        se.printStackTrace();
	    }
	}

}
