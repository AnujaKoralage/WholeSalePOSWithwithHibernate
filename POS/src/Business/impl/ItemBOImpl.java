package Business.impl;

import Business.custom.ItemBO;
import DAO.DAO.custom.OrderItemsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DAO.custom.Impl.OrderItemsDAOImpl;
import DAO.DAO.custom.ItemDao;
import DTO.ItemDTO;
import Entities.Item;
import Entities.OrderItems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {
    private ItemDao itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    public List<ItemDTO> getAllItems() throws Exception {

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

    public boolean saveItem(ItemDTO itemDTO) throws Exception {
        return itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getQty(), itemDTO.getPrice()));

    }

    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);

    }

    public boolean itemExistsinOrder(String id) throws Exception {
        OrderItemsDAO orderDetailsDAO = new OrderItemsDAOImpl();
        List<OrderItems> orderItems = orderDetailsDAO.findAll();

        for (OrderItems orderitem:orderItems) {
            if (orderitem.getOrderItemsPK().getItemcode().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));

    }

}
