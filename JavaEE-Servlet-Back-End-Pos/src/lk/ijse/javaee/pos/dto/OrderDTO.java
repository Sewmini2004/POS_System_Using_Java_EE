package lk.ijse.javaee.pos.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String orId;
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private Date orDate;
    private String orcustomer_id;
    private double orDis;
    private double orSubTotal;
    private List<Order_DetailsDTO> orderDetails;

    public OrderDTO(String orId, Date orDate, String orcustomer_id, double orDis, double orSubTotal) {
        this.orId = orId;
        this.orDate = orDate;
        this.orcustomer_id = orcustomer_id;
        this.orDis = orDis;
        this.orSubTotal = orSubTotal;
    }
}



