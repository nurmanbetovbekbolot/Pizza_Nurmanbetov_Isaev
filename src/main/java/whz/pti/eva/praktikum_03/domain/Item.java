package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Item implements Serializable{

//    @Id
//    @GeneratedValue
//    private Long id;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String itemId;


    private int quantity;

    @ManyToOne
    private Pizza pizza;

    @Enumerated(EnumType.STRING)
    private PizzaSize pizzaSize;


}
