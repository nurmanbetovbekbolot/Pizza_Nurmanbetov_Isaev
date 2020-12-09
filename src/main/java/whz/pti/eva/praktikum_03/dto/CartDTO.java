package whz.pti.eva.praktikum_03.dto;

import lombok.*;
import whz.pti.eva.praktikum_03.domain.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * The class Cart dto.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
    private Long id;
    private int quantity=0;
    private Map<String, Item> items = new HashMap<>();
    private String userId;

    /**
     * Increment.
     */
    public void increment(){
        quantity++;
    }
}
