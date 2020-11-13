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
public class Item implements Serializable {

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


//    @ManyToOne
//    private Pizza itemId;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @Override
    public int hashCode() {
        if (getItemId() != null) {
            return getItemId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return getItemId() != null && getItemId().equals(other.getItemId());
    }

}
