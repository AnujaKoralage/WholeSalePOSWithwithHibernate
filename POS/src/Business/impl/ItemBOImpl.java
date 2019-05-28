package Business.impl;

import Business.custom.ItemBO;
import DAO.DAO.custom.OrderItemsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DAO.custom.Impl.ItemDAOImpl;
import DAO.custom.Impl.OrderItemsDAOImpl;
import DAO.DAO.custom.ItemDao;
import DTO.ItemDTO;
import Entities.Item;
import Entities.OrderItems;
import UtilityClasses.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {
    private ItemDAOImpl itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public List<ItemDTO> getAllItems() throws Exception {

        itemDAO.setSession(session);
        return itemDAO.findAll().stream().map(new Function<Item, ItemDTO>() {
            @Override
            public ItemDTO apply(Item item) {
                return new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice());
            }
        }).collect(Collectors.toList());

        /*List<Item> items = itemDAO.findAll();
        ArrayList<ItemDTO> list = new ArrayList<>();

        for (Item item:items) {
            list.add(new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice()));
        }

        return list;*/
    }

    public void saveItem(ItemDTO itemDTO) throws Exception {
        itemDAO.setSession(session);
        itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getQty(), itemDTO.getPrice()));

    }

    public void deleteItem(String id) throws Exception {
        itemDAO.setSession(session);
        itemDAO.delete(id);

    }

    public boolean itemExistsinOrder(String id) throws Exception {
        itemDAO.setSession(session);
        OrderItemsDAO orderDetailsDAO = new OrderItemsDAOImpl();
        List<OrderItems> orderItems = orderDetailsDAO.findAll();

        for (OrderItems orderitem:orderItems) {
            if (orderitem.getOrderItemsPK().getItemcode().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void updateItem(ItemDTO item) throws Exception {
        itemDAO.setSession(session);
        itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
    }

}
