package com.ebook.dal;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
  
public class DBHelper {

	public static Connection getConnection() {
		 
		System.out.println("DBHelper: -------- PostgreSQL " + "JDBC Connection  ------------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("DBHelper: Check Where  your PostgreSQL JDBC Driver exist and " + "Include in your library path!");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: PostgreSQL JDBC Driver Registered!");
 
		Connection connection = null;
 
		try {
			
            connection = C3PO.comboPooledDataSource.getConnection();
		    //String dbUrl = "jdbc:postgresql://ec2-54-236-137-173.compute-1.amazonaws.com:5432/dc37f4f559s4o8";
		    //System.out.println("URL " + dbUrl);
			//connection = DriverManager.getConnection(dbUrl, "vleimbeveodmce", "e31a27af1ef200c44c5d9e43c07eac442ba3b202650eeb90a719653b311d3cce");
 

				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT VERSION()");

	            if (rs.next()) {
	                System.out.println("DBHelper: The Database Version is " + rs.getString(1));
	            }
 
		} catch (SQLException e) {
 
			System.out.println("DBHelper: Connection Failed! Check output console");
			e.printStackTrace();
			return null;
 
		}
 
		System.out.println("DBHelper: You have a database connection!");
		
		return connection;
	}
}
