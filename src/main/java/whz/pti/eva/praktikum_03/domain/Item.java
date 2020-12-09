package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.*;
import java.io.Serializable;

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
