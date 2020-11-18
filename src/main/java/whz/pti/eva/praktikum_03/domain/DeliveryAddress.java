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
public class DeliveryAddress extends BaseEntity<Long>{

    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;

    @ManyToMany(mappedBy = "deliveryAddress")
    private List<Customer> customer;
}
