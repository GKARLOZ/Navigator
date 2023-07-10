package com.solvd.navigator.factory;

import com.solvd.navigator.dao.IDAO;

public abstract class AbstractFactory {
    public abstract <T extends IDAO> T getDao(DaoType daoType);
}