package Business.custom;

import DTO.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    public List<CustomerDTO> allCustomers() throws Exception;
    public void saveCustomer(CustomerDTO customer) throws Exception;
    public boolean deleteCustomer(String id) throws Exception;
    public boolean customerExistsinOrder(String id) throws Exception;
    public boolean updateCustomer(CustomerDTO customer) throws Exception;

}
