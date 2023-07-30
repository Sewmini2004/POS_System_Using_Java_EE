package lk.ijse.javaee.pos.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String orId;
    private Date orDate;
    private String orcustomer_id;
    private double orDis;
    private double orSubTotal;

}



