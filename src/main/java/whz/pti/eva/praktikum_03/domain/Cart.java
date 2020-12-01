package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cart extends BaseEntity<Long> {

    private int quantity;

//    @OneToOne
    @JoinColumn(referencedColumnName = "user_id")
    private String userId;

    @OneToMany
    private Map<String, Item> items;

}
