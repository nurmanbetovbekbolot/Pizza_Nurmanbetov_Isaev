package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * The class Item.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Item extends BaseEntity<String> {

    private int quantity;

    @ManyToOne
    private Pizza pizza;

    @Enumerated(EnumType.STRING)
    private PizzaSize pizzaSize;


}
