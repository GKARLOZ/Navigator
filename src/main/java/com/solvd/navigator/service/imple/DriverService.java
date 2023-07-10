package com.solvd.navigator.service.imple;

import com.solvd.navigator.dao.IDriverDAO;
import com.solvd.navigator.factory.AbstractFactory;
import com.solvd.navigator.factory.DaoType;
import com.solvd.navigator.factory.FactoryGenerator;
import com.solvd.navigator.factory.FactoryType;
import com.solvd.navigator.model.Driver;
import com.solvd.navigator.service.IDriverService;

import java.util.List;

public class DriverService implements IDriverService {

    private AbstractFactory daoFactory = FactoryGenerator.getFactory(FactoryType.JDBC);
    private IDriverDAO iDriverDAO = daoFactory.getDao(DaoType.DRIVER);

    @Override
    public Driver getById(long id) {
        return iDriverDAO.getById(id);
    }

    @Override
    public List<Driver> getAll() {
        return iDriverDAO.getAll();
    }

    @Override
    public void insert(Driver driver) {
        iDriverDAO.insert(driver);
    }

    @Override
    public void update(Driver driver) {
        iDriverDAO.update(driver);
    }

    @Override
    public void delete(long id) {
        iDriverDAO.delete(id);
    }
}
