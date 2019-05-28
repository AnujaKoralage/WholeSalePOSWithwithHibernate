package Entities;

public class OrderItemsPK {

    private String orderid;
    private String itemcode;

    public OrderItemsPK() {
    }

    public OrderItemsPK(String orderid, String itemcode) {
        this.orderid = orderid;
        this.itemcode = itemcode;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
}
