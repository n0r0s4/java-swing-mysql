package com.proven.customersguiapp.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author outsider
 */
public class DBConnect {
    
    private static final DBConnect instance = null;
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://"+
            "localhost/customersdb";
    private static final String USUARI = "customerjava";
    private static final String PASSWORD = "customerjava";

    private DBConnect() throws ClassNotFoundException {
        Class.forName(this.DRIVER);       
    }
    
    /**
     * implements singleton pattern.
     * @return singleton instance of DBConnect class.
     * @throws ClassNotFoundException if database api driver can not be loaded.
     */
    public static DBConnect getInstance() throws ClassNotFoundException {
        if (instance == null) {
            return new DBConnect();
        }
        else {
            return instance;
        }
    }
    
    /**
     * 
     * @return a connection 
     * @throws SQLException if a connection error occurs
     */
    public Connection getConnection() throws SQLException {
        Connection conn=DriverManager.getConnection(BD_URL, USUARI, PASSWORD);
        return conn;
    }
}
