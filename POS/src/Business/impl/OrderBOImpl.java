package Business.impl;

import Business.custom.OrderBO;
import DAO.CRUDDAO;
import DAO.DAO.custom.CustomerDAO;
import DAO.DAO.custom.ItemDao;
import DAO.DAO.custom.OrderItemsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DAO.custom.Impl.OrderDetailsDAOImpl;
import DTO.ItemDTO;
import DTO.OrderDetailsDTO;
import Entities.Item;
import Entities.OrderDetails;
import Entities.OrderItems;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderBOImpl implements OrderBO {

public String qtyGetByCode(String itemcode){
    OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
    return orderDetailsDAO.getQtyByCode(itemcode);
}

    public void insertOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {

        CRUDDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
        dao.save(new OrderDetails(orderDetailsDTO.getOrderid(),orderDetailsDTO.getCusid(),orderDetailsDTO.getOrderdate()));
    }

    public void insertOrderItems(OrderDetailsDTO orderDetailsDTO) throws Exception {

        CRUDDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_ITEMS);
        dao.save(new OrderItems(orderDetailsDTO.getOrderid(),orderDetailsDTO.getCusid(),orderDetailsDTO.getOrderdate()));
    }

    public void updateItemQty(String qtyOnHand, String itemcode){
        OrderItemsDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_ITEMS);
        dao.updateQtyOnHand(qtyOnHand,itemcode);
    }

    public List<ItemDTO> allItems() throws Exception {
        ItemDao dao = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

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
        CustomerDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
        ObservableList id = dao.getId(list);

        return id;
    }


}
