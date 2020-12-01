package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.security.domain.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer extends BaseEntity<Long> {


    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "UUID2")
    @Column(name = "user_id",unique = true)
    private String userId;

    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    private Boolean isActive;



    @OneToOne
    @JoinColumn(name = "sec_user_id")
    private User user;

    @ManyToMany
    private List<DeliveryAddress> deliveryAddress;
}
