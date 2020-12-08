package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cart extends BaseEntity<Long> {

    private int quantity = 0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_items",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    @MapKey(name = "id")
    private Map<String, Item> items = new HashMap<>();


}