package com.solvd.navigator.service;

import com.solvd.navigator.App;
import com.solvd.navigator.dao.*;
import com.solvd.navigator.dao.impl.mybatis.*;
import com.solvd.navigator.factory.AbstractFactory;
import com.solvd.navigator.factory.DaoType;
import com.solvd.navigator.factory.FactoryGenerator;
import com.solvd.navigator.factory.FactoryType;
import com.solvd.navigator.model.*;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class DaoTesting {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(App.class);
    private static final Random random = new Random();
    private  static AbstractFactory daoFactory = FactoryGenerator.getFactory(FactoryType.JDBC);

    public static void DLTest(){

        IDriverLicenseDAO driverLicenseDAO = daoFactory.getDao(DaoType.DRIVER_LICENSE);

        DriverLicense driverLicense = createDriverLicense();
        DriverLicense driverLicense2 = createDriverLicense();

        try {
            driverLicenseDAO.insert(driverLicense);
            logger.info(driverLicenseDAO.getById(driverLicense.getId()).toString() + "\n");

            driverLicense.setNumber(Math.abs(random.nextInt()));
            driverLicenseDAO.update(driverLicense);
            logger.info(driverLicenseDAO.getById(driverLicense.getId()).toString() + "\n");

            driverLicenseDAO.insert(driverLicense2);
            logger.info(driverLicenseDAO.getAll().toString() + "\n");
        } catch (Exception e) {
            logger.error(e);
        } finally {
            driverLicenseDAO.delete(driverLicense.getId());
            driverLicenseDAO.delete(driverLicense2.getId());
        }
    }

    public static void TransportationTypeTest(){


        ITransportationTypeDAO transportationTypeDAO = daoFactory.getDao(DaoType.TRANSPORTATION_TYPE);

        TransportationType transportationType = createTransportationType("Bus");
        TransportationType transportationType2 = createTransportationType("Plane");

        try {
            transportationTypeDAO.insert(transportationType);
            logger.info(transportationTypeDAO.getById(transportationType.getId()).toString() + "\n");

            transportationType.setType("Train");
            transportationTypeDAO.update(transportationType);
            logger.info(transportationTypeDAO.getById(transportationType.getId()).toString() + "\n");

            transportationTypeDAO.insert(transportationType2);
            logger.info(transportationTypeDAO.getAll().toString() + "\n");
        } catch (Exception e) {
            logger.error(e);
        } finally {
            transportationTypeDAO.delete(transportationType.getId());
            transportationTypeDAO.delete(transportationType2.getId());
        }
    }

    public static void ReviewTest(){
        IReviewDAO reviewDAO = daoFactory.getDao(DaoType.REVIEW);
        ILocationDAO locationDAO = daoFactory.getDao(DaoType.LOCATION);

        Location location = createLocation("Los Angeles", new Coordinate(34.0522, -118.2437));

        Review review = createReview("content from review", location);
        Review review2 = createReview("content from review2", location);

        try {
            locationDAO.insert(location);
            reviewDAO.insert(review);
            logger.info(reviewDAO.getById(review.getId()).toString() + "\n");

            review.setContent("updated content from review");
            System.out.println();
            reviewDAO.update(review);
            logger.info(reviewDAO.getById(review.getId()).toString() + "\n");

            reviewDAO.insert(review2);
            logger.info(reviewDAO.getAll().toString() + "\n");
        } catch (Exception e) {
            logger.error(e);
        } finally {
            reviewDAO.delete(review.getId());
            reviewDAO.delete(review2.getId());
            locationDAO.delete(location.getId());
        }
    }

    public static void PersonTest(){

        //Test PersonDAO without DriverLicense
        IPersonDAO personDAO = daoFactory.getDao(DaoType.PERSON);


        Person person = createPerson("John");
        Person person2 = createPerson("Jack");


        try{

            personDAO.insert(person);
            logger.info(personDAO.getById(person.getId()).toString() + "\n");

            person.setName("Bob");
            personDAO.update(person);
            logger.info(personDAO.getById(person.getId()).toString() + "\n");

            personDAO.insert(person2);
            logger.info(personDAO.getAll().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }
        finally {
            personDAO.delete(person.getId());
            personDAO.delete(person2.getId());
        }
    }

    public static void PersonDriverTest(){
        // Test PersonDAO with DriverLicense
        IPersonDAO personDAO = daoFactory.getDao(DaoType.PERSON);
        IDriverLicenseDAO driverLicenseDAO = daoFactory.getDao(DaoType.DRIVER_LICENSE);

        DriverLicense driverLicense = createDriverLicense();
        DriverLicense driverLicense2 = createDriverLicense();

        Person driver = createDriver("John", driverLicense);
        Person driver2 = createDriver("Jack", driverLicense2);

        try{
            driverLicenseDAO.insert(driverLicense);
            driverLicenseDAO.insert(driverLicense2);

            personDAO.insertDriver(driver);
            logger.info(personDAO.getDriverById(driver.getId()).toString() + "\n");

            driver.setName("Bob");
            personDAO.updateDriver(driver);
            logger.info(personDAO.getDriverById(driver.getId()).toString() + "\n");

            personDAO.insertDriver(driver2);
            logger.info(personDAO.getAllDrivers().toString() + "\n");
        }catch (Exception e){
            logger.error(e);
        }finally {
            personDAO.delete(driver.getId());
            personDAO.delete(driver2.getId());
            driverLicenseDAO.delete(driverLicense.getId());
            driverLicenseDAO.delete(driverLicense2.getId());
        }
    }

    public static void LocationTest() {
        // Test LocationDAO, route list is initialized in service layer
        ILocationDAO locationDAO = daoFactory.getDao(DaoType.LOCATION);
        Location location1 = createLocation("New York", new Coordinate(40.7128, 74.0060));
        Location location2 = createLocation("Los Angeles", new Coordinate(34.0522, 118.2437));

        //locationDAO.insert(location1);

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

    public static void TransportationTest(){
        // Test TransportationDAO
        ITransportationTypeDAO transportationTypeDAO = daoFactory.getDao(DaoType.TRANSPORTATION_TYPE);
        ITransportationDAO transportationDAO = daoFactory.getDao(DaoType.TRANSPORTATION);
        IDriverLicenseDAO driverLicenseDAO = daoFactory.getDao(DaoType.DRIVER_LICENSE);
        IPersonDAO driverDAO = daoFactory.getDao(DaoType.PERSON);

        DriverLicense driverLicense = createDriverLicense();
        DriverLicense driverLicense2 = createDriverLicense();

        Person driver = createDriver("John", driverLicense);
        Person driver2 = createDriver("Jack", driverLicense2);

        TransportationType transportationType = createTransportationType("Bus");
        TransportationType transportationType2 = createTransportationType("Plane");

        Transportation transportation1 = createTransportation(driver, transportationType);
        Transportation transportation2 = createTransportation(driver2, transportationType2);

        try {
            transportationTypeDAO.insert(transportationType);
            transportationTypeDAO.insert(transportationType2);
            driverLicenseDAO.insert(driverLicense);
            driverLicenseDAO.insert(driverLicense2);
            driverDAO.insert(driver);
            driverDAO.insert(driver2);

            transportationDAO.insert(transportation1);
            logger.info(transportationDAO.getById(transportation1.getId()).toString() + "\n");

            transportation1.setVehicleNumber(102);
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
            driverDAO.delete(driver2.getId());
            driverLicenseDAO.delete(driverLicense.getId());
            driverLicenseDAO.delete(driverLicense2.getId());
            transportationTypeDAO.delete(transportationType.getId());
            transportationTypeDAO.delete(transportationType2.getId());
        }
    }

    public static void RouteTest(){
        IDriverLicenseDAO driverLicenseDAO = daoFactory.getDao(DaoType.DRIVER_LICENSE);
        DriverLicense driverLicense = createDriverLicense();
        DriverLicense driverLicense2 = createDriverLicense();

        ITransportationTypeDAO transportationTypeDAO = daoFactory.getDao(DaoType.TRANSPORTATION_TYPE);
        TransportationType transportationType = createTransportationType("Bus");
        TransportationType transportationType2 = createTransportationType("Plane");

        ILocationDAO locationDAO = daoFactory.getDao(DaoType.LOCATION);
        Location locationA = createLocation("Chicago", new Coordinate(41.8781, 87.6298));
        Location locationB = createLocation("San Francisco", new Coordinate(37.7749, 122.4194));
        Location locationC = createLocation("Houston", new Coordinate(29.7604, 95.3698));

        IPersonDAO personDAO = daoFactory.getDao(DaoType.PERSON);
        Person driver = createDriver("John", driverLicense);
        Person driver2 = createDriver("Jack", driverLicense2);

        ITransportationDAO transportationDAO = daoFactory.getDao(DaoType.TRANSPORTATION);
        Transportation transportation = createTransportation(driver, transportationType);
        Transportation transportation2 = createTransportation(driver2, transportationType2);

        IRouteDAO routeDAO = daoFactory.getDao(DaoType.ROUTE);
        Route route = createRoute(locationA, locationB, transportation,100, 1000);
        Route route2 = createRoute(locationB, locationA, transportation2,100, 1500);

        try{
            driverLicenseDAO.insert(driverLicense);
            driverLicenseDAO.insert(driverLicense2);
            transportationTypeDAO.insert(transportationType);
            transportationTypeDAO.insert(transportationType2);
            locationDAO.insert(locationA);
            locationDAO.insert(locationB);
            locationDAO.insert(locationC);
            personDAO.insertDriver(driver);
            personDAO.insertDriver(driver2);
            transportationDAO.insert(transportation);
            transportationDAO.insert(transportation2);

            routeDAO.insert(route);
            logger.info(routeDAO.getById(route.getId()) + "\n");

            route.setLocationA(locationC);
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
            personDAO.delete(driver.getId());
            personDAO.delete(driver2.getId());
            locationDAO.delete(locationA.getId());
            locationDAO.delete(locationB.getId());
            locationDAO.delete(locationC.getId());
            transportationTypeDAO.delete(transportationType.getId());
            transportationTypeDAO.delete(transportationType2.getId());
            driverLicenseDAO.delete(driverLicense.getId());
            driverLicenseDAO.delete(driverLicense2.getId());
        }
    }

    //    // Helpers for creating model objects
    private static DriverLicense createDriverLicense(){
        long id = Math.abs(random.nextLong());
        int number = Math.abs(random.nextInt());
        DriverLicense driverLicense = new DriverLicense();
        driverLicense.setId(id);
        driverLicense.setNumber(number);
        return driverLicense;
    }

    private static Person createDriver(String name, DriverLicense driverLicense){
        long id = Math.abs(random.nextLong());
        Person driver = new Person();
        driver.setId(id);
        driver.setName(name);
        driver.setDriverLicense(driverLicense);
        return driver;
    }

    private static TransportationType createTransportationType(String type){
        long id = Math.abs(random.nextLong());
        TransportationType transportationType = new TransportationType();
        transportationType.setId(id);
        transportationType.setType(type);
        return transportationType;
    }

    private static Location createLocation(String name, Coordinate coordinate){
        long id = Math.abs(random.nextLong());
        Location location = new Location();
        location.setId(id);
        location.setName(name);
        location.setCoordinate(coordinate);
        return location;
    }

    private static Review createReview(String content, Location location){
        long id = Math.abs(random.nextLong());
        Review review = new Review();
        review.setId(id);
        review.setContent(content);
        review.setLocation(location);
        return review;
    }

    private static Person createPerson(String name){
        long id = Math.abs(random.nextLong());
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    private static Transportation createTransportation(Person driver, TransportationType transportationType){
        long id = Math.abs(random.nextLong());
        Transportation transportation = new Transportation();
        transportation.setId(id);
        transportation.setCost(100);
        transportation.setVehicleNumber(101);
        transportation.setDriver(driver);
        transportation.setTransportationType(transportationType);
        return transportation;
    }

    private static Route createRoute(Location locationA, Location locationB, Transportation transportation,  int duration, int distance){
        long id = Math.abs(random.nextLong());
        Route route = new Route();
        route.setId(id);
        route.setLocationA(locationA);
        route.setLocationB(locationB);
        route.setTransportation(transportation);
        route.setDuration(duration);
        route.setDistance(distance);
        return route;
    }
}
