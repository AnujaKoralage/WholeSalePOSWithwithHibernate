package DAO;

import DAO.DAO.custom.CustomerDAO;
import DAO.DAO.custom.ItemDao;
import DAO.custom.Impl.CustomerDAOImpl;
import DAO.custom.Impl.ItemDAOImpl;
import DAO.custom.Impl.OrderDetailsDAOImpl;
import DAO.custom.Impl.OrderItemsDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
            return daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ITEM:
                return (T) new ItemDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ORDER:
                return (T) new OrderDetailsDAOImpl();
            case ORDER_ITEMS:
                return (T) new OrderItemsDAOImpl();
            default:
                return null;
        }
    }

    /*public CustomerDAO getCustomerDAO(){
        return new CustomerDAOImpl();
  }

    public ItemDao getItemDAO(){
        return new ItemDAOImpl();
  }*/
}
