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
public class Customer extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    private boolean isActive;

    @ManyToMany
    private List<DeliveryAddress> deliveryAddress;
}
