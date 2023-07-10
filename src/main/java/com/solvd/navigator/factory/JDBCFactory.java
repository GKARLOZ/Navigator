package com.solvd.navigator.factory;

import com.solvd.navigator.dao.IDAO;
import com.solvd.navigator.dao.jdbc.RouteDAO;
import com.solvd.navigator.dao.jdbc.TransportationDAO;
import com.solvd.navigator.dao.jdbc.LocationDAO;
import com.solvd.navigator.dao.jdbc.DriverDAO;
public class JDBCFactory extends AbstractFactory{
    public <T extends IDAO> T getDao(DaoType daoType) {

        if (daoType == null) {
            return null;
        }

        switch (daoType) {
            case DRIVER:
                return (T) new DriverDAO();
            case LOCATION:
                return (T) new LocationDAO();
            case ROUTE:
                return (T) new RouteDAO();
            case TRANSPORTATION:
                return (T) new TransportationDAO();
            default:
                return null;
        }
    }


    }
