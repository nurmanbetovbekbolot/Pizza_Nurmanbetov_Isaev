package whz.pti.eva.praktikum_03.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Pizza extends BaseEntity<Long>{


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
//        Pizza other = (Pizza) obj;
//        return getId() != null && getId().equals(other.getId());
//    }
}
