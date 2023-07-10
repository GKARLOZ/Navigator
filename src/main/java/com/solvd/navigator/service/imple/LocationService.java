package com.solvd.navigator.service.imple;

import com.solvd.navigator.dao.*;
import com.solvd.navigator.factory.AbstractFactory;
import com.solvd.navigator.factory.DaoType;
import com.solvd.navigator.factory.FactoryGenerator;
import com.solvd.navigator.factory.FactoryType;
import com.solvd.navigator.model.Location;
import com.solvd.navigator.model.Route;
import com.solvd.navigator.model.Transportation;
import com.solvd.navigator.service.ILocationService;

import java.util.List;

public class LocationService implements ILocationService {

    private AbstractFactory daoFactory = FactoryGenerator.getFactory(FactoryType.JDBC);
    private ITransportationDAO transportationDAO = daoFactory.getDao(DaoType.TRANSPORTATION);
    private IRouteDAO routedao = daoFactory.getDao(DaoType.ROUTE);
    private ILocationDAO locationDAO = daoFactory.getDao(DaoType.LOCATION);
    private IDriverDAO driverDAO = daoFactory.getDao(DaoType.DRIVER);

    @Override
    public Location getById(long id) {

        Location location = locationDAO.getById(id);
        List<Route> routeList = routedao.getAllRoutesByLocationId(id);

        for(Route route: routeList){

            route.setLocationA(locationDAO.getById(route.getLocationA().getId()));
            route.setLocationB(locationDAO.getById(route.getLocationB().getId()));
            Transportation transportation = transportationDAO.getById(route.getTransportation().getId());
            transportation.setDriver(driverDAO.getById(transportation.getDriver().getId()));
            route.setTransportation(transportation);

        }
        location.setRoutes(routeList);

        return location;
    }

    @Override
    public List<Location> getAll() {

        List<Location> locationsList = locationDAO.getAll();
        List<Route> routeList = null;

        for(Location loca: locationsList) {
            routeList = routedao.getAllRoutesByLocationId(loca.getId());
            for (Route route : routeList) {

                route.setLocationA(locationDAO.getById(route.getLocationA().getId()));
                route.setLocationB(locationDAO.getById(route.getLocationB().getId()));
                Transportation transportation = transportationDAO.getById(route.getTransportation().getId());
                transportation.setDriver(driverDAO.getById(transportation.getDriver().getId()));
                route.setTransportation(transportation);

            }
            loca.setRoutes(routeList);
        }


        return locationsList;

    }

    @Override
    public void insert(Location location) {
        locationDAO.insert(location);
    }

    @Override
    public void update(Location location) {
        locationDAO.update(location);
    }

    @Override
    public void delete(long id) {
        locationDAO.delete(id);
    }
}
