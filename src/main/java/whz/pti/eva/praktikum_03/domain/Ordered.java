package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Ordered.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Ordered extends BaseEntity<Long> {

    private int numberOfItems = 0;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private Customer userId;

    @OneToMany
    private List<OrderedItem> items = new ArrayList<>();

}