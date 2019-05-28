package Business;

import Business.custom.SuperBO;
import Business.impl.CustomerBOImpl;
import Business.impl.OrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public <T extends SuperBO> T getDAO(BOTypes types){
        switch (types){
            case ORDER:
                return (T) new OrderBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new OrderBOImpl();
                default:
                    return null;
        }
    }

    public static BOFactory getInstance(){
        if (boFactory == null)
            boFactory = new BOFactory();
        return boFactory;
    }
}
