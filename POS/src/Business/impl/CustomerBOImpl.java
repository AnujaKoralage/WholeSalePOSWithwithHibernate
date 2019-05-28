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
import UtilityClasses.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
    private Session session = HibernateUtil.getSessionFactory().openSession();

    public List<CustomerDTO> allCustomers() throws Exception {
        customerDAO.setSession(session);
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
        customerDAO.setSession(session);
        Customer cusentity = new Customer(customer.getId(),customer.getName(),customer.getAddress());

        CustomerDAO cusDao = new CustomerDAOImpl();
        cusDao.save(cusentity);
        
    }

    public void deleteCustomer(String id) throws Exception {
        customerDAO.setSession(session);
        customerDAO.delete(id);
    }

    public boolean customerExistsinOrder(String id) throws Exception {
        customerDAO.setSession(session);
        OrderDetailsDAO od = new OrderDetailsDAOImpl();
        ((OrderDetailsDAOImpl) od).setSession(session);

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

    public void updateCustomer(CustomerDTO customer) throws Exception {
        customerDAO.setSession(session);
        customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
    }

}
