package whz.pti.eva.praktikum_03.dto;

import lombok.*;
import whz.pti.eva.praktikum_03.domain.Customer;

import java.util.List;

/**
 * The class Delivery address dto.
 *
 * @author Isaev A. Nurmanbetov B.
 */
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
    /**
     * The Customer list.
     */
    List<Customer> customerList;
}
