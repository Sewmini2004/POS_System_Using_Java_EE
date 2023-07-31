package lk.ijse.javaee.pos.dao;


import lk.ijse.javaee.pos.dao.custom.impl.*;


import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public enum DaoTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAILS
    }

    public <T extends SuperDAO> T getDao(DaoTypes types) {
        switch (types) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImple();
            case ORDER:
                return (T) new OrderDAOImple();
            case ORDER_DETAILS:
                return (T) new Order_DetailesDAOImple();

            default:
                return null;
        }

    }
}