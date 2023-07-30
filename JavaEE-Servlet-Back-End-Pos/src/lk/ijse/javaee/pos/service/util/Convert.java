package lk.ijse.javaee.pos.service.util;

import lk.ijse.javaee.pos.dto.CustomerDTO;
import lk.ijse.javaee.pos.dto.ItemDTO;
import lk.ijse.javaee.pos.dto.OrderDTO;
import lk.ijse.javaee.pos.dto.Order_DetailsDTO;
import lk.ijse.javaee.pos.entity.Customer;
import lk.ijse.javaee.pos.entity.ItemEntity;
import lk.ijse.javaee.pos.entity.OrderEntity;
import lk.ijse.javaee.pos.entity.Order_DetailesEntity;

public class Convert {


    // from______    entity -> DTO
    //to______       DTO   -> entity
    public static CustomerDTO fromCutomer(Customer customer){
        return new CustomerDTO(
                customer.getCusId(),
                customer.getCusName(),
                customer.getCusAddress(),
                customer.getCusSalary()
        );
    }



    public static Customer toCustomer(CustomerDTO customerDTO){

        return new Customer(
                customerDTO.getCusId(),
                customerDTO.getCusName(),
                customerDTO.getCusAddress(),
                customerDTO.getCusSalary()
        );
    }




    public static ItemDTO fromItem(ItemEntity itemEntity){
        return new ItemDTO(
                itemEntity.getItemCode(),
                itemEntity.getItemName(),
                itemEntity.getQtyOnHand(),
                itemEntity.getItemPrice()
        );

    }

    public static ItemEntity toItem(ItemDTO itemDTO){
      return new ItemEntity(
              itemDTO.getItemCode(),
              itemDTO.getItemName(),
              itemDTO.getQtyOnHand(),
              itemDTO.getItemPrice()
      );
    }

    public static OrderDTO fromOrder(OrderEntity orderEntity){
         return new OrderDTO(
                orderEntity.getOrId(),
                orderEntity.getOrDate(),
                orderEntity.getOrcustomer_id(),
                orderEntity.getOrDis(),
                orderEntity.getOrSubTotal()
         );
    }
  public static OrderEntity toOrder(OrderDTO orderDTO){
      return new OrderEntity(
              orderDTO.getOrId(),
              orderDTO.getOrDate(),
              orderDTO.getOrcustomer_id(),
              orderDTO.getOrDis(),
              orderDTO.getOrSubTotal()
      );

  }


    public static Order_DetailsDTO fromOrder_Detailes(Order_DetailesEntity order_detailesEntity){
        return new Order_DetailsDTO(
                order_detailesEntity.getId(),
                order_detailesEntity.getOrItemCOde(),
                order_detailesEntity.getOrder_id(),
                order_detailesEntity.getOrItemPrice(),
                order_detailesEntity.getOrItemQTY(),
                order_detailesEntity.getOrItemTotal()
        );
    }

    public static Order_DetailesEntity toOrder_Detailes(Order_DetailsDTO order_detailsDTO){
        return new Order_DetailesEntity(
                order_detailsDTO.getId(),
                order_detailsDTO.getOrItemCOde(),
                order_detailsDTO.getOrder_id(),
                order_detailsDTO.getOrItemPrice(),
                order_detailsDTO.getOrItemQTY(),
                order_detailsDTO.getOrItemTotal()
        );
    }



}
