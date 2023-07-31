package lk.ijse.javaee.pos.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private double cusSalary;

}
