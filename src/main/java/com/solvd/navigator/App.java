package com.solvd.navigator;

import com.solvd.navigator.connection.ConnectionPool;
import com.solvd.navigator.dao.*;
import com.solvd.navigator.dao.impl.mybatis.DriverDAO;
import com.solvd.navigator.dao.impl.mybatis.LocationDAO;
import com.solvd.navigator.dao.impl.mybatis.TransportationDAO;
import com.solvd.navigator.dao.impl.mybatis.RouteDAO;
import com.solvd.navigator.model.*;
import com.solvd.navigator.algo.*;
import com.solvd.navigator.service.ILocationService;
import com.solvd.navigator.service.IRouteService;
import com.solvd.navigator.service.ITransportationService;
import com.solvd.navigator.service.imple.LocationService;
import com.solvd.navigator.service.imple.RouteService;
import com.solvd.navigator.service.imple.TransportationService;
import org.apache.logging.log4j.Logger;


import java.util.Dictionary;
import java.util.Random;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(App.class);
    private static final Random random = new Random();

    public static void main(String[] args) {


        //getDirectionsUsingService();
        getDirections();

        //ServiceTesting.driverCRUD();
       //ServiceTesting.locationCRUD();
        //ServiceTesting.transportationCRUD();
        //ServiceTesting.routeCRUD();

        //connectionPoolTest();
        //myBatisDriverTest();
        //myBatisLocationTest();
        //myBatisTransportationTest();
        //myBatisRouteTest();
    }

    public static void getDirectionsUsingService(){

        Graph graph = new Graph(true, true);
        ILocationService locationService = new LocationService();
        ITransportationService transportationService = new TransportationService();
        IRouteService routeService = new RouteService();

        Location Ontario = graph.addLocation(locationService.getById(4837167663264298753L));
        //Location NewYork = graph.addLocation(locationService.getById(1709880993726761196L));
        Location LosAngeles = graph.addLocation(locationService.getById(8604220953550867941L));
        Location LosVegas = graph.addLocation(locationService.getById(7289402658460834839L));


        graph.addRoute(routeService.getById(777L));
        graph.addRoute(routeService.getById(888L));
        graph.addRoute(routeService.getById(555L));

        Location startingLocation = LosVegas;
        Location endingLocation = LosAngeles;

        Dictionary[] result = Dijkstra.findAllShortestPath(graph, startingLocation);
        Dictionary<String, Integer> duration = result[0];
        Dictionary<String, Location> previous = result[1];
        

//        Integer durationToDestination = duration.get("Brooklyn");
//        System.out.println("Shortest distance from " + startingLocation.getName() +" to "+ endingLocation.getName() +": " + durationToDestination);
        System.out.print("Path: ");

        Dijkstra.printPathWithTransportation2(previous, endingLocation);
        Result result1 = Dijkstra.result(previous, endingLocation);
        System.out.println("\nResult: "+ result1.toString());


    }
    public static void getDirections(){

        Graph graph = new Graph(true, true);

        Location LA = graph.addLocation("Los Angeles");
        Location LV = graph.addLocation("Los Vegas");
        Location NY = graph.addLocation("New York");
        Location SF = graph.addLocation("San Fransisco");
        Location Bro = graph.addLocation("Brooklyn");
        Location Ho = graph.addLocation("Hobart");
        Location Mi = graph.addLocation("Miami");
        Location Sea = graph.addLocation("Seattle");

        Transportation bus = new Transportation("Bus");
        Transportation train = new Transportation("Train");
        Transportation plane = new Transportation("Plane");

        graph.addRoute(LA,LV,3,bus);
        graph.addRoute(LA,SF,8,train);
        graph.addRoute(SF,LV,7,train);
        graph.addRoute(LV,NY,5,plane);
        graph.addRoute(NY,Bro,3,bus);
        graph.addRoute(Bro,Ho,8,train);
        graph.addRoute(Ho,Mi,7,train);
        graph.addRoute(Mi,Sea,5,plane);

        graph.addRoute(LA,Bro,44,train);
        graph.addRoute(LA,Mi,7,train);
        graph.addRoute(Mi,Bro,5,plane);


        Location startingLocation = LA;
        Location endingLocation = Bro;

        Dictionary[] result = Dijkstra.findAllShortestPath(graph, startingLocation);
        Dictionary<String, Integer> duration = result[0];
        Dictionary<String, Location> previous = result[1];

        Integer durationToDestination = duration.get("Brooklyn");
        System.out.println("Shortest distance from " + startingLocation.getName() +" to "+ endingLocation.getName() +": " + durationToDestination);
        System.out.print("Path: ");

        Dijkstra.printPathWithTransportation(previous, endingLocation);
        Result result1 = Dijkstra.result(previous, endingLocation);
        System.out.println("\nResult: "+ result1.toString());

    }

    private static void connectionPoolTest(){
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

    private static void myBatisDriverTest(){
        // Test DriverDAO
        IDriverDAO driverDAO = new DriverDAO();
        Driver driver = createDriver("John");
        Driver driver2 = createDriver("Jack");

        try{
            driverDAO.insert(driver);
            logger.info(driverDAO.getById(driver.getId()).toString() + "\n");

            driver.setName("Bob");
            driverDAO.update(driver);
            logger.info(driverDAO.getById(driver.getId()).toString() + "\n");

            driverDAO.insert(driver2);
            logger.info(driverDAO.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            driverDAO.delete(driver.getId());
            driverDAO.delete(driver2.getId());
        }
    }

    private static void myBatisLocationTest() {
        // Test LocationDAO, route list is initialized in service layer
        ILocationDAO locationDAO = new LocationDAO();
        Location location1 = createLocation("New York");
        Location location2 = createLocation("Los Angeles");

        try {
            locationDAO.insert(location1);
            logger.info(locationDAO.getById(location1.getId()).toString() + "\n");

            location1.setName("Chicago");
            locationDAO.update(location1);
            logger.info(locationDAO.getById(location1.getId()).toString() + "\n");

            locationDAO.insert(location2);
            logger.info(locationDAO.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            locationDAO.delete(location1.getId());
            locationDAO.delete(location2.getId());
        }
    }

    private static void myBatisTransportationTest(){
        // Test TransportationDAO
        IDriverDAO driverDAO = new DriverDAO();
        Driver driver = createDriver("John");

        ITransportationDAO transportationDAO = new TransportationDAO();
        Transportation transportation1 = createTransportation("Bus", driver);
        Transportation transportation2 = createTransportation("Train", driver);

        try {
            driverDAO.insert(driver);
            transportationDAO.insert(transportation1);
            logger.info(transportationDAO.getById(transportation1.getId()).toString() + "\n");

            transportation1.setName("Plane");
            transportationDAO.update(transportation1);
            logger.info(transportationDAO.getById(transportation1.getId()).toString() + "\n");

            transportationDAO.insert(transportation2);
            logger.info(transportationDAO.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            transportationDAO.delete(transportation1.getId());
            transportationDAO.delete(transportation2.getId());
            driverDAO.delete(driver.getId());
        }
    }

    private static void myBatisRouteTest(){
        IDriverDAO driverDAO = new DriverDAO();
        Driver driver = createDriver("John");

        ITransportationDAO transportationDAO = new TransportationDAO();
        Transportation transportation = createTransportation("Bus", driver);
        Transportation transportation2 = createTransportation("Train", driver);

        ILocationDAO locationDAO = new LocationDAO();
        Location locationA = createLocation("New York");
        Location locationB = createLocation("Los Angeles");

        IRouteDAO routeDAO = new RouteDAO();
        Route route = createRoute(locationA, locationB, transportation, 10, 100, 1000);
        Route route2 = createRoute(locationB, locationA, transportation, 10, 100, 1500);

        try{
            driverDAO.insert(driver);
            transportationDAO.insert(transportation);
            transportationDAO.insert(transportation2);
            locationDAO.insert(locationA);
            locationDAO.insert(locationB);
            routeDAO.insert(route);
            logger.info(routeDAO.getById(route.getId()) + "\n");

            route.setTransportation(transportation2);
            routeDAO.update(route);
            logger.info(routeDAO.getById(route.getId()) + "\n");

            routeDAO.insert(route2);
            logger.info(routeDAO.getAll() + "\n");
        }catch (Exception e) {
            logger.error(e);
        }finally {
            routeDAO.delete(route.getId());
            routeDAO.delete(route2.getId());
            transportationDAO.delete(transportation.getId());
            transportationDAO.delete(transportation2.getId());
            driverDAO.delete(driver.getId());
            locationDAO.delete(locationA.getId());
            locationDAO.delete(locationB.getId());
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
