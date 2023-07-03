package com.solvd.navigator;

import com.solvd.navigator.connection.ConnectionPool;
import com.solvd.navigator.dao.IDriverDAO;
import com.solvd.navigator.dao.impl.mybatis.DriverDAO;
import com.solvd.navigator.model.Driver;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(App.class);

    public static void main(String[] args) {
        //  connectionPoolTest();
        myBatisTest();
    }

    public static void connectionPoolTest(){
        // Create an instance of ConnectionPool with a desired pool size
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        try {
            // Acquire a connection from the pool
            Connection connection = connectionPool.getConnection();

            // Ensure the connection is not null
            if (connection != null) {
                logger.info("Connection acquired successfully!\n");

                // Release the connection back to the pool
                connectionPool.releaseConnection(connection);
                logger.info("Connection released successfully!");
            } else {
                logger.info("Failed to acquire connection from the pool.");
            }
        } catch (InterruptedException | SQLException | IOException e) {
            logger.error("Failed to acquire/release connection from the pool.", e);
        }
    }

    public static void myBatisTest(){
        IDriverDAO driverDAO = new DriverDAO();
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setName("John");
        driverDAO.insert(driver);
        logger.info("Driver inserted successfully!");
    }
}
