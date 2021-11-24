package com.ebook.dal;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3PO {
	
    static ComboPooledDataSource comboPooledDataSource = null;
    
    static {
        comboPooledDataSource = new ComboPooledDataSource();
 
        comboPooledDataSource.setJdbcUrl("jdbc:postgresql://ec2-54-236-137-173.compute-1.amazonaws.com:5432/dc37f4f559s4o8");
        comboPooledDataSource.setUser("vleimbeveodmce");
        comboPooledDataSource.setPassword("e31a27af1ef200c44c5d9e43c07eac442ba3b202650eeb90a719653b311d3cce");
 
        comboPooledDataSource.setMinPoolSize(3);
        comboPooledDataSource.setAcquireIncrement(3);
        comboPooledDataSource.setMaxPoolSize(30);
 
    }

}
