package whz.pti.eva.praktikum_03.dto;

import lombok.*;
import whz.pti.eva.praktikum_03.domain.Customer;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryAddressDTO {
    private Long id;
    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;
    List<Customer> customerList;
}
