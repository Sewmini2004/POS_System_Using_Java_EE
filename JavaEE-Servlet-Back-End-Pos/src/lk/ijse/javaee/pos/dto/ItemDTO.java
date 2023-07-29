package lk.ijse.javaee.pos.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private String code;
    private String name;
    private int qty;
    private double unit_price;

}