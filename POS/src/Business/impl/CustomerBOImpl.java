package Business.impl;

import Business.custom.CustomerBO;
import DAO.DAO.custom.CustomerDAO;
import DAO.DAO.custom.OrderDetailsDAO;
import DAO.DAOFactory;
import DAO.DAOTypes;
import DAO.custom.Impl.CustomerDAOImpl;
import DAO.custom.Impl.OrderDetailsDAOImpl;
import DTO.CustomerDTO;
import Entities.Customer;
import Entities.OrderDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    public List<CustomerDTO> allCustomers() throws Exception {
        return customerDAO.findAll().stream().map(new Function<Customer, CustomerDTO>() {
            @Override
            public CustomerDTO apply(Customer customer) {
                return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            }
        }).collect(Collectors.toList());


        /*ArrayList<CustomerDTO> customerlist = new ArrayList<>();
        for (Customer customer:customerDAO.findAll()) {
            customerlist.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerlist;*/
    }

    public void saveCustomer(CustomerDTO customer) throws Exception {
        Customer cusentity = new Customer(customer.getId(),customer.getName(),customer.getAddress());

        CustomerDAO cusDao = new CustomerDAOImpl();
        cusDao.save(cusentity);
        
    }

    public boolean deleteCustomer(String id) throws Exception {
        boolean b = customerDAO.delete(id);

        return b;

    }

    public boolean customerExistsinOrder(String id) throws Exception {
        OrderDetailsDAO od = new OrderDetailsDAOImpl();

        /*Stream<OrderDetails> orderDetailsStream = od.findAll().stream().filter(new Predicate<OrderDetails>() {
            @Override
            public boolean test(OrderDetails orderDetails) {
                if (orderDetails.getCusid().equals(id)) {
                    return true;
                }
                return false;
            }
        });*/

        List<OrderDetails> orderDetails = od.findAll();

        for (OrderDetails order:orderDetails) {
            if (order.getCusid().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        boolean b = customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress()));

        return b;
    }

}
