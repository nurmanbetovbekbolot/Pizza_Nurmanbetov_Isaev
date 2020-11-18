package whz.pti.eva.praktikum_03.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer extends BaseEntity<Long>{

    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;

    @ManyToMany
    private List<DeliveryAddress> deliveryAddress;
}
