package lk.ijse.javaee.pos.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order_DetailesEntity {
    private String id;
    private String orItemCOde;
    private String order_id;
    private double orItemPrice;
    private int orItemQTY;
    private double  orItemTotal;


}
