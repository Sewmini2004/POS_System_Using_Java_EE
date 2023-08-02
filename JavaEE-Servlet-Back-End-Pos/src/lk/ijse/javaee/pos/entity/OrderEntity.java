package lk.ijse.javaee.pos.entity;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderEntity {
    private String orId;
    private String orcustomer_id;
    private Date orDate;
    private double orDis;
    private double orSubTotal;
}
