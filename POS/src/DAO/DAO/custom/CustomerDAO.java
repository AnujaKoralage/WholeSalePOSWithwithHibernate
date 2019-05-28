package DAO.DAO.custom;

import DAO.CRUDDAO;
import DAO.SuperDAO;
import Entities.Customer;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CRUDDAO<Customer,String> {
    public ObservableList getId(ObservableList list) throws SQLException;


}
