package DAO.custom.Impl;

import DAO.DAO.custom.CustomerDAO;
import Entities.Customer;
import UtilityClasses.DBConnection;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?,?,?)";

        return CRUDUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress());

    }

    public boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name=?,address=? WHERE id=?";

        return CRUDUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getId());
    }

    public  boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id=?";

        return CRUDUtil.execute(sql,id);
    }

    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        ResultSet rst = CRUDUtil.execute(sql);
        ArrayList<Customer> arrayList = new ArrayList<>();

        while (rst.next()){
            arrayList.add(new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address")));
        }
        return arrayList;
    }

    public Customer find(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id=?";
        ResultSet rst = CRUDUtil.execute(sql,id);
        if (rst.next())
            return new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"));
        else
            return null;
    }

    public ObservableList getId(ObservableList cids) throws SQLException {
        String sql1 = "SELECT id FROM customer";
        ResultSet rst1 = CRUDUtil.execute(sql1);

        while (rst1.next()){
            cids.add(rst1.getString("id"));
        }
        return cids;
    }

}
