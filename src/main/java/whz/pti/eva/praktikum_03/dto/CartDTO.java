package whz.pti.eva.praktikum_03.dto;

import lombok.*;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.Item;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
    private int quantity=0;
    private Map<String, Item> items = new HashMap<>();
    private String userId;

    public void incrementQuantity() {
        this.quantity++;
    }
}
