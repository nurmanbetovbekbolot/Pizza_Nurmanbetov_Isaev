package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.security.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer extends BaseEntity<String> {


    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    private Boolean isActive;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
}