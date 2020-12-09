package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * The class Delivery address.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class DeliveryAddress extends BaseEntity<Long> {

    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;

    /**
     * Instantiates a new Delivery address.
     *
     * @param street      the street
     * @param houseNumber the house number
     * @param town        the town
     * @param postalCode  the postal code
     */
    public DeliveryAddress(String street, String houseNumber, String town, String postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.postalCode = postalCode;
    }

    @ManyToMany(mappedBy = "deliveryAddress")
    private List<Customer> customer;
}
