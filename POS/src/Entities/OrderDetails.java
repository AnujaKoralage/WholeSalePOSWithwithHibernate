package Entities;

public class OrderDetails extends SuperEntity{
    private String orderid;
    private String cusid;
    private String orderdate;

    public OrderDetails() {
    }

    public OrderDetails(String orderid, String cusid, String orderdate) {
        this.orderid = orderid;
        this.cusid = cusid;
        this.orderdate = orderdate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderid='" + orderid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                '}';
    }
}
