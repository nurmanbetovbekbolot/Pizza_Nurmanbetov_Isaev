package whz.pti.eva.praktikum_03.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Ordered {
    @Id
    @GeneratedValue
    private Long id;

    private int numberOfitems;
    private String userId;

    @OneToMany
    private List<OrderedItem> items = new ArrayList<>();

}
