package com.solvd.navigator.service;

import com.solvd.navigator.App;
import com.solvd.navigator.connection.ConnectionPool;
import com.solvd.navigator.model.*;
import com.solvd.navigator.service.imple.DriverService;
import com.solvd.navigator.service.imple.LocationService;
import com.solvd.navigator.service.imple.RouteService;
import com.solvd.navigator.service.imple.TransportationService;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class ServiceTesting {

    //GO TO DAOFactory file inside the DAO folder to test different versions(myBatis or JDBC) of DAO.

    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(App.class);
    private static final Random random = new Random();
    private static IDriverService driverService = new DriverService();
    private static ILocationService locationService = new LocationService();
    private static ITransportationService transportationService = new TransportationService();
    private static IRouteService routeService = new RouteService();


    public static void connectionPoolTest(){
        // Create an instance of ConnectionPool with a desired pool size
        ConnectionPool connectionPool = ConnectionPool.getInstance();

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

    public static void driverCRUD(){
        // Test DriverDAO
        //IDriverDAO driverDAO = new DriverDAO();

        Driver driver = createDriver("Juanito");
        Driver driver2 = createDriver("Jack");

        try{
            driverService.insert(driver);
            logger.info(driverService.getById(driver.getId()).toString() + "\n");

            driver.setName("Bob");
            driverService.update(driver);
            logger.info(driverService.getById(driver.getId()).toString() + "\n");

            driverService.insert(driver2);
            logger.info(driverService.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            driverService.delete(driver.getId());
            driverService.delete(driver2.getId());
        }
    }

    public static void locationCRUD() {
        // Test LocationDAO, route list is initialized in service layer
        //ILocationDAO locationDAO = new LocationDAO();
        Location location1 = createLocation("New York");
        Location location2 = createLocation("Los Angeles");

        try {
            locationService.insert(location1);
            logger.info(locationService.getById(location1.getId()).toString() + "\n");

            location1.setName("Chicago");
            locationService.update(location1);
            logger.info(locationService.getById(location1.getId()).toString() + "\n");

            locationService.insert(location2);
            logger.info(locationService.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            locationService.delete(location1.getId());
            locationService.delete(location2.getId());
        }
    }

    public static void transportationCRUD(){
        // Test TransportationDAO
        //IDriverDAO driverDAO = new DriverDAO();
        Driver driver = createDriver("John");

        //ITransportationDAO transportationDAO = new TransportationDAO();
        Transportation transportation1 = createTransportation("Bus", driver);
        Transportation transportation2 = createTransportation("Train", driver);

        try {
            driverService.insert(driver);
            transportationService.insert(transportation1);
            logger.info(transportationService.getById(transportation1.getId()).toString() + "\n");

            transportation1.setName("Plane");
            transportationService.update(transportation1);
            logger.info(transportationService.getById(transportation1.getId()).toString() + "\n");

            transportationService.insert(transportation2);
            logger.info(transportationService.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            transportationService.delete(transportation1.getId());
            transportationService.delete(transportation2.getId());
            driverService.delete(driver.getId());
        }
    }

    public static void routeCRUD(){
        //IDriverDAO driverDAO = new DriverDAO();
        Driver driver = createDriver("Jack");

        //ITransportationDAO transportationDAO = new TransportationDAO();
        Transportation transportation = createTransportation("Bus", driver);
        Transportation transportation2 = createTransportation("Train", driver);

        //ILocationDAO locationDAO = new LocationDAO();
        Location locationA = createLocation("Ontario");
        Location locationB = createLocation("Palo Alto");

        //IRouteDAO routeDAO = new RouteDAO();
        Route route = createRoute(locationA, locationB, transportation, 10, 100, 1000);
        Route route2 = createRoute(locationB, locationA, transportation, 10, 100, 1500);

        try{
            driverService.insert(driver);
            transportationService.insert(transportation);
            transportationService.insert(transportation2);
            locationService.insert(locationA);
            locationService.insert(locationB);
            routeService.insert(route);
            logger.info(routeService.getById(route.getId()) + "\n");

            route.setTransportation(transportation2);
            routeService.update(route);
            logger.info(routeService.getById(route.getId()) + "\n");

            routeService.insert(route2);
            logger.info(routeService.getAll() + "\n");
        }catch (Exception e) {
            logger.error(e);
        }finally {
            routeService.delete(route.getId());
            routeService.delete(route2.getId());
            transportationService.delete(transportation.getId());
            transportationService.delete(transportation2.getId());
            driverService.delete(driver.getId());
            locationService.delete(locationA.getId());
            locationService.delete(locationB.getId());
        }


    }

    // Helpers for creating model objects
    private static Driver createDriver(String name){
        long id = Math.abs(random.nextLong());
        Driver driver = new Driver();
        driver.setId(id);
        driver.setName(name);
        return driver;
    }

    private static Transportation createTransportation(String name, Driver driver){
        long id = Math.abs(random.nextLong());
        Transportation transportation = new Transportation();
        transportation.setId(id);
        transportation.setName(name);
        transportation.setDriver(driver);
        return transportation;
    }

    private static Location createLocation(String name){
        long id = Math.abs(random.nextLong());
        Location location = new Location();
        location.setId(id);
        location.setName(name);
        return location;
    }

    private static Route createRoute(Location locationA, Location locationB, Transportation transportation, int duration, int distance, int cost){
        long id = Math.abs(random.nextLong());
        Route route = new RouteBuilder().createRoute();
        route.setId(id);
        route.setLocationA(locationA);
        route.setLocationB(locationB);
        route.setTransportation(transportation);
        route.setDuration(duration);
        route.setDistance(distance);
        route.setCost(cost);
        return route;
    }


}
