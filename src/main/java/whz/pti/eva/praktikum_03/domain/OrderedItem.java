package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class OrderedItem {

    @Id
    @GeneratedValue
    private Long id;

    private long pizzaId;
    private String name;
    private int quantity;
    private String userId;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;
}
