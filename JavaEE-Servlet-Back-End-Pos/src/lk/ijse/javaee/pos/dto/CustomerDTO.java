package lk.ijse.javaee.pos.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private double cusSalary;

}
