package whz.pti.eva.praktikum_03.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Ordered extends BaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfItems;
    private String userId;

    @OneToMany
    private List<OrderedItem> items;

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
//        Ordered other = (Ordered) obj;
//        return getId() != null && getId().equals(other.getId());
//    }

}
