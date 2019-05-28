package Business.impl;

import Business.custom.OrderBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.CustomerDAO;
import DAO.DAO.custom.ItemDao;
import DAO.DAO.custom.OrderDetailsDAO;
import DAO.DAO.custom.OrderItemsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DAO.custom.Impl.CustomerDAOImpl;
import DAO.custom.Impl.ItemDAOImpl;
import DAO.custom.Impl.OrderDetailsDAOImpl;
import DAO.custom.Impl.OrderItemsDAOImpl;
import DTO.ItemDTO;
import DTO.OrderDetailsDTO;
import Entities.Item;
import Entities.OrderDetails;
import Entities.OrderItems;
import UtilityClasses.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderBOImpl implements OrderBO {

public String qtyGetByCode(String itemcode){
    Session session = HibernateUtil.getSessionFactory().openSession();
    OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
    orderDetailsDAO.setSession(session);
    return orderDetailsDAO.getQtyByCode(itemcode);
}

    public void insertOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {

    try(Session session = HibernateUtil.getSessionFactory().openSession()) {

        session.beginTransaction();
        OrderDetailsDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
        dao.setSession(session);
        //dao.save(new OrderDetails(orderDetailsDTO.getOrderid(),orderDetailsDTO.getCusid(),orderDetailsDTO.getOrderdate()));
        session.getTransaction().commit();
    }
    }

    public void insertOrderItems(OrderDetailsDTO orderDetailsDTO) throws Exception {
    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        session.beginTransaction();
        OrderItemsDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_ITEMS);
        dao.setSession(session);
        dao.save(new OrderItems(orderDetailsDTO.getOrderid(),orderDetailsDTO.getCusid(),orderDetailsDTO.getOrderdate()));
        session.getTransaction().commit();
        session.close();
    }
    }

    public void updateItemQty(String qtyOnHand, String itemcode){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            OrderItemsDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_ITEMS);
            dao.setSession(session);
            dao.updateQtyOnHand(qtyOnHand,itemcode);
            session.getTransaction().commit();
        }
    }

    public List<ItemDTO> allItems() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ItemDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
        dao.setSession(session);

        return dao.findAll().stream().map(new Function<Item, ItemDTO>() {
            @Override
            public ItemDTO apply(Item item) {
                return new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice());
            }
        }).collect(Collectors.toList());

        /*List<Item> all = dao.findAll();

        List<ItemDTO> list  = new ArrayList<>();
        for (Item item:all) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice()));
        }

        return list;*/
    }

    public ObservableList getAllCustomerId(ObservableList list) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CustomerDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
        dao.setSession(session);
        ObservableList id = dao.getId(list);

        return id;
    }


}
