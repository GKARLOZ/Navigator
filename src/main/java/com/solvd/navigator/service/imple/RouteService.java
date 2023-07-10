package com.solvd.navigator.service.imple;

import com.solvd.navigator.dao.*;
import com.solvd.navigator.factory.AbstractFactory;
import com.solvd.navigator.factory.DaoType;
import com.solvd.navigator.factory.FactoryGenerator;
import com.solvd.navigator.factory.FactoryType;
import com.solvd.navigator.model.Route;
import com.solvd.navigator.model.Transportation;
import com.solvd.navigator.service.IRouteService;

import java.util.List;

public class RouteService implements IRouteService {

    private AbstractFactory daoFactory = FactoryGenerator.getFactory(FactoryType.JDBC);
    private ITransportationDAO transportationDAO = daoFactory.getDao(DaoType.TRANSPORTATION);
    private IRouteDAO routedao = daoFactory.getDao(DaoType.ROUTE);
    private ILocationDAO locationDAO = daoFactory.getDao(DaoType.LOCATION);
    private IDriverDAO driverDAO = daoFactory.getDao(DaoType.DRIVER);

    @Override
    public Route getById(long id) {

        Route route = routedao.getById(id);
        route.setLocationA(locationDAO.getById(route.getLocationA().getId()));
        route.setLocationB(locationDAO.getById(route.getLocationB().getId()));
        Transportation transportation = transportationDAO.getById(route.getTransportation().getId());
        transportation.setDriver(driverDAO.getById(transportation.getDriver().getId()));
        route.setTransportation(transportation);
        return route;
    }

    @Override
    public List<Route> getAll() {
        List<Route> routeList = routedao.getAll();
        for(Route route: routeList){

            route.setLocationA(locationDAO.getById(route.getLocationA().getId()));
            route.setLocationB(locationDAO.getById(route.getLocationB().getId()));
            Transportation transportation = transportationDAO.getById(route.getTransportation().getId());
            transportation.setDriver(driverDAO.getById(transportation.getDriver().getId()));
            route.setTransportation(transportation);

        }

        return routeList;
    }

    @Override
    public void insert(Route route) {
        routedao.insert(route);
    }

    @Override
    public void update(Route route) {
        routedao.update(route);
    }

    @Override
    public void delete(long id) {
        routedao.delete(id);
    }
}
