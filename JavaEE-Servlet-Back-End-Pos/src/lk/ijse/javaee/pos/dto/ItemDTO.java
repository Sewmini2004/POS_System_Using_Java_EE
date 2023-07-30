package lk.ijse.javaee.pos.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {
    private String itemCode;
    private String itemName;
    private int qtyOnHand;
    private double itemPrice;

}
