package lk.ijse.javaee.pos.service;
import lk.ijse.javaee.pos.service.custome.imple.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory() {
    if (serviceFactory==null){
        serviceFactory = new ServiceFactory();
    }
    return serviceFactory;
    }
    public enum ServiceType{
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS
    }
    public <T extends SuperBO>T getService(ServiceType serviceType) {
      switch (serviceType){
          case CUSTOMER:
              return (T) new CustomerBOImple();
          case ITEM:
              return (T) new ItemBOImple();
          case ORDER:
              return (T) new OrderBOImple();
          case ORDER_DETAILS:
              return (T) new Order_DetailesBOImple();
           default:
         return null;

      }
    }
}
