package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderedItem extends BaseEntity<Long> {


//    @OneToOne
//    private Pizza pizzaId;

    private Long pizzaId;
    private String name;
    private int quantity;
    private String userId;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

}
