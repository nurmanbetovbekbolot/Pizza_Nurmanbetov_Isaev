package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cart extends BaseEntity<Long> {

    private int quantity;

    private String userId;

    @OneToMany
    private Map<String, Item> items;

}
