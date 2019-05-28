package DAO.DAO.custom;

import DAO.CRUDDAO;
import DAO.SuperDAO;
import Entities.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CRUDDAO<OrderDetails,String> {
    public String getQtyByCode(String itemcode);

}
