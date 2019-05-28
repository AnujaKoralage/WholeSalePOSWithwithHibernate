package DAO.DAO.custom;

import DAO.CRUDDAO;
import DAO.SuperDAO;
import Entities.OrderItems;
import Entities.OrderItemsPK;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemsDAO extends CRUDDAO<OrderItems,OrderItemsPK> {
    void updateQtyOnHand( String itemcode,String qtyOnHand);

}
