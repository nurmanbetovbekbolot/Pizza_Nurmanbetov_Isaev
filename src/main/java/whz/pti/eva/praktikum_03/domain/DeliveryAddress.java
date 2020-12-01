package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

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

    public DeliveryAddress(String street, String houseNumber, String town, String postalCode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.postalCode = postalCode;
    }

    @ManyToMany(mappedBy = "deliveryAddress")
    private List<Customer> customer;
}
