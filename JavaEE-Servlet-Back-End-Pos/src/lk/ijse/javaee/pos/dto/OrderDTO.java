package lk.ijse.javaee.pos.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String id;
    private String customer_id;
    private Date date;
    private double discount;
    private double sub_total;
}
