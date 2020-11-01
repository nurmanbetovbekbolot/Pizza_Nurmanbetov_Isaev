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
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String itemId;
    private int quantity;

    @ManyToOne
    private Pizza pizza;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

}
