package DAO.custom.Impl;

import DAO.DAO.custom.OrderItemsDAO;
import Entities.OrderItems;
import Entities.OrderItemsPK;
import UtilityClasses.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDAOImpl implements OrderItemsDAO {

    public boolean save(OrderItems orderitems) throws SQLException {
        String sql = "INSERT INTO orderitems VALUES(?,?,?)";

        return CRUDUtil.execute(sql,orderitems.getOrderItemsPK().getOrderid(),orderitems.getOrderItemsPK().getItemcode(),orderitems.getQty());

    }

    public boolean update(OrderItems orderitems) throws SQLException {
        String sql = "UPDATE orderitems SET itemCode=?,qty=? WHERE id=?";

        return CRUDUtil.execute(sql,orderitems.getOrderItemsPK().getItemcode(),orderitems.getQty(),orderitems.getOrderItemsPK().getOrderid());
    }

    public  boolean delete(OrderItemsPK orderItemsPK) throws SQLException {
        String sql = "DELETE FROM orderitems WHERE orderid=? AND itemcode=?";
        return CRUDUtil.execute(sql,orderItemsPK.getOrderid(),orderItemsPK.getItemcode());
    }

    public List<OrderItems> findAll() throws SQLException {
        String sql = "SELECT * FROM orderitems";

        ResultSet rst = CRUDUtil.execute(sql);
        ArrayList<OrderItems> arrayList = new ArrayList<>();

        while (rst.next()){
            arrayList.add(new OrderItems(new OrderItemsPK(rst.getString("orderid"),rst.getString("itemcode")),rst.getString("qty")));
        }
        return arrayList;
    }

    public OrderItems find(OrderItemsPK orderItemsPK) throws SQLException {
        String sql = "SELECT * FROM orderitems WHERE orderid=? AND itemCode=?";

        ResultSet rst = CRUDUtil.execute(sql,orderItemsPK.getOrderid(),orderItemsPK.getItemcode());
        if (rst.next()){
            return new OrderItems(new OrderItemsPK(rst.getString("orderid"),rst.getString("itemcode")),rst.getString("qty"));
        }
        else
            return null;

    }

    @Override
    public void updateQtyOnHand(String itemcode,String qtyOnHand) {
        String sql3 = "UPDATE item SET qty=? WHERE code=?";
        try {
            CRUDUtil.execute(sql3,qtyOnHand,itemcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
