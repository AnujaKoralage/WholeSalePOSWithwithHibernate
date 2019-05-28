package DAO.custom.Impl;

import DAO.DAO.custom.ItemDao;
import Entities.Item;
import UtilityClasses.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDao {

    public boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO Item VALUES(?,?,?,?)";

        return CRUDUtil.execute(sql,item.getCode(),item.getDescription(),item.getQty(),item.getPrice());

    }

    public boolean update(Item item) throws SQLException {
        String sql = "UPDATE Item SET description=?,qty=?,price=? WHERE code=?";

        return CRUDUtil.execute(sql,item.getDescription(),item.getQty(),item.getPrice(),item.getCode());
    }

    public  boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM Item WHERE code=?";

        return CRUDUtil.execute(sql,code);
    }

    public List<Item> findAll() throws SQLException {
        String sql = "SELECT * FROM Item";

        ArrayList<Item> arrayList = new ArrayList<>();
        ResultSet rst = CRUDUtil.execute(sql);

        while (rst.next()){
            arrayList.add(new Item(rst.getString("code"),rst.getString("description"),rst.getString("qty"),rst.getString("price")));
        }
        return arrayList;
    }

    public Item find(String code) throws SQLException {
        String sql = "SELECT * FROM Item WHERE code=?";

        ResultSet rst = CRUDUtil.execute(sql,code);
        if (rst.next())
            return new Item(rst.getString("code"),rst.getString("description"),rst.getString("qty"),rst.getString("price"));
        else
            return null;
    }
    
}
