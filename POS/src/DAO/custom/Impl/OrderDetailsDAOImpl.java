package DAO.custom.Impl;

import DAO.DAO.custom.OrderDetailsDAO;
import Entities.OrderDetails;
import UtilityClasses.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    public boolean save(OrderDetails orderdetails) throws SQLException {
        String sql = "INSERT INTO orderdetails VALUES(?,?,?)";

        return CRUDUtil.execute(sql,orderdetails.getOrderid(),orderdetails.getCusid(),orderdetails.getOrderdate());

    }

    public boolean update(OrderDetails orderdetails) throws SQLException {
        String sql = "UPDATE orderdetails SET cusid=?,orderdate=? WHERE orderid=?";

        return CRUDUtil.execute(sql,orderdetails.getCusid(),orderdetails.getOrderdate(),orderdetails.getOrderid());
    }

    public  boolean delete(String orderid) throws SQLException {
        String sql = "DELETE FROM orderdetails WHERE orderid=?";

        return CRUDUtil.execute(sql,orderid);
    }

    public List<OrderDetails> findAll() throws SQLException {
        String sql = "SELECT * FROM orderdetails";

        ResultSet rst = CRUDUtil.execute(sql);
        ArrayList<OrderDetails> arrayList = new ArrayList<>();

        while (rst.next()){
            arrayList.add(new OrderDetails(rst.getString("orderid"),rst.getString("cusid"),rst.getString("orderdate")));
        }
        return arrayList;
    }

    public OrderDetails find(String orderid) throws SQLException {
        String sql = "SELECT * FROM orderdetails WHERE orderid=?";

        ResultSet rst = CRUDUtil.execute(sql,orderid);
        if (rst.next()){
            return new OrderDetails(rst.getString("orderid"),rst.getString("cusid"),rst.getString("orderdate"));
        }
        else
            return null;
    }

    @Override
    public String getQtyByCode(String itemcode) {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT qty FROM item WHERE code=?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,itemcode);

            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                return rst.getString("qty");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
