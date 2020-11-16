package whz.pti.eva.praktikum_03.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cart extends BaseEntity<Long> implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    private String userId;

    @OneToMany
    private Map<String, Item> items;

    //    @Override
//    public int hashCode() {
//        if (getId() != null) {
//            return getId().hashCode();
//        }
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        Cart other = (Cart) obj;
//        return getId() != null && getId().equals(other.getId());
//    }
}
