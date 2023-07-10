package com.solvd.navigator.service.imple;

import com.solvd.navigator.dao.IDriverDAO;
import com.solvd.navigator.dao.ITransportationDAO;
import com.solvd.navigator.factory.AbstractFactory;
import com.solvd.navigator.factory.DaoType;
import com.solvd.navigator.factory.FactoryGenerator;
import com.solvd.navigator.factory.FactoryType;
import com.solvd.navigator.model.Transportation;
import com.solvd.navigator.service.ITransportationService;

import java.util.List;

public class TransportationService implements ITransportationService {

    private AbstractFactory daoFactory = FactoryGenerator.getFactory(FactoryType.JDBC);
    private ITransportationDAO iTransportationDAO = daoFactory.getDao(DaoType.TRANSPORTATION);
    private IDriverDAO iDriverDAO = daoFactory.getDao(DaoType.DRIVER);

    @Override
    public Transportation getById(long id) {

        Transportation transportation = iTransportationDAO.getById(id);
        transportation.setDriver(iDriverDAO.getById(transportation.getDriver().getId()));

        return transportation;
    }

    @Override
    public List<Transportation> getAll() {

        List<Transportation> transportationList = iTransportationDAO.getAll();

        for(Transportation transportation: transportationList){

            transportation.setDriver(iDriverDAO.getById(transportation.getDriver().getId()));

        }
        return transportationList;
    }

    @Override
    public void insert(Transportation transportation) {
        iTransportationDAO.insert(transportation);
    }

    @Override
    public void update(Transportation transportation) {
        iTransportationDAO.update(transportation);
    }

    @Override
    public void delete(long id) {
        iTransportationDAO.delete(id);
    }
}
