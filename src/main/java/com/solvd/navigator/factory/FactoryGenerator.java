package com.solvd.navigator.factory;

public class FactoryGenerator {
    public static AbstractFactory getFactory(FactoryType choice) {
        
        switch (choice) {
            case MYBATIS:
                return new MyBatisFactory();
            case JDBC:
                return new JDBCFactory();
            default:
                return null;
        }
    }
}
