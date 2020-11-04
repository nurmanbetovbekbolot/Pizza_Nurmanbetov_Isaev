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

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        OrderedItem other = (OrderedItem) obj;
        return getId() != null && getId().equals(other.getId());
    }
}