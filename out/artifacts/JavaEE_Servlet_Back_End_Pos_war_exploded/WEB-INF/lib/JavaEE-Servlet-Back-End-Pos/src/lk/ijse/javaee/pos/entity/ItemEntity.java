package lk.ijse.javaee.pos.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemEntity {
    private String itemCode;
    private String itemName;
    private int qtyOnHand;
    private double itemPrice;

}
