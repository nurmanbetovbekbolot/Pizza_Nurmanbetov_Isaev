package whz.pti.eva.praktikum_03.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class DeliveryAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String street;
    private String housenumber;
    private String town;
    private String postalCode;

    @ManyToMany(mappedBy = "deliveryAdress")
    private List<Customer> customer;
}
