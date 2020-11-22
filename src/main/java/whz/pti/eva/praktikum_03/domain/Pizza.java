package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Pizza extends BaseEntity<Long> {


    private String name;
    private BigDecimal priceLarge;
    private BigDecimal priceMedium;
    private BigDecimal priceSmall;

    public BigDecimal getPriceByEnum(PizzaSize pizzaSize){
        switch (pizzaSize){
            case LARGE: return this.getPriceLarge();
            case MEDIUM: return this.getPriceMedium();
            case SMALL: return this.getPriceSmall();
        }
        return null;
    }

}
