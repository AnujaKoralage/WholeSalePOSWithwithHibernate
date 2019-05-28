package DAO.custom.Impl;

import DAO.DAO.custom.ItemDao;
import Entities.Item;
import UtilityClasses.DBConnection;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDao {

    private Session session;

    public void save(Item item) throws Exception {
        session.save(item);
    }

    public void update(Item item) throws Exception {
        session.merge(item);
    }

    public void delete(String code) throws Exception {
        Item item = session.load(Item.class,code);
        session.delete(item);
    }

    public List<Item> findAll() throws Exception {
        List<Item> list = session.createNativeQuery("SELECT * FROM Item", Item.class).list();

        return list;
    }

    public Item find(String code) throws Exception {
        Item item = session.createNativeQuery("SELECT * FROM Item WHERE code=?", Item.class).setParameter(1,code).uniqueResult();
        return item;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
