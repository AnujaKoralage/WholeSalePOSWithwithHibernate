package Business.custom;

import DTO.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO{

    public List<ItemDTO> getAllItems() throws Exception;
    public boolean saveItem(ItemDTO itemDTO) throws Exception;
    public boolean deleteItem(String id) throws Exception;
    public boolean itemExistsinOrder(String id) throws Exception;
    public boolean updateItem(ItemDTO item) throws Exception;

}
