package DAO.custom.Impl;

import DAO.DAO.custom.CustomerDAO;
import Entities.Customer;
import UtilityClasses.DBConnection;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class CustomerDAOImpl implements CustomerDAO {

    private Session session;
    @Override
    public void save(Customer customer) throws Exception {
        session.save(customer);
    }

    public void update(Customer customer) throws Exception {
        session.merge(customer);
    }

    public  void delete(String id) throws Exception {
        Customer customer = session.load(Customer.class, id);
        session.delete(customer);
    }

    public List<Customer> findAll() throws Exception {

        List<Customer> list = session.createQuery("From Entities.Customer",Customer.class).list();

        return list;
    }

    public Customer find(String id) throws Exception {
        Customer customer = session.createNativeQuery("SELECT * FROM customer WHERE id=?", Customer.class).setParameter(1,id).uniqueResult();
        return customer;
    }

    public ObservableList getId(ObservableList cids) throws SQLException {
        List<Customer> cus = session.createNativeQuery("SELECT id FROM customer", Customer.class).list();
        for (int i=0;i<cus.size();i++){
            cids.add(cus.get(i).getId());
        }
        return cids;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
