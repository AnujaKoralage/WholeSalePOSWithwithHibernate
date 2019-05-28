package Entities;

public class OrderItems extends SuperEntity{

    private OrderItemsPK orderItemsPK;
    private String qty;

    public OrderItems() {
    }

    public OrderItems(OrderItemsPK orderItemsPK, String qty) {
        this.orderItemsPK = orderItemsPK;
        this.qty = qty;
    }

    public OrderItems(String orderid, String itemcode,String qty) {
        this.orderItemsPK = new OrderItemsPK(orderid,itemcode);
        this.qty = qty;
    }

    public OrderItemsPK getOrderItemsPK() {
        return orderItemsPK;
    }

    public void setOrderItemsPK(OrderItemsPK orderItemsPK) {
        this.orderItemsPK = orderItemsPK;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderItemsPK=" + orderItemsPK +
                ", qty='" + qty + '\'' +
                '}';
    }
}
