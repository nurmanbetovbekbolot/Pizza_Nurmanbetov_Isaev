package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * The class Ordered item.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderedItem extends BaseEntity<Long> {

    private Long pizzaId;
    private String name;
    private int quantity;
    private String userId;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

}
