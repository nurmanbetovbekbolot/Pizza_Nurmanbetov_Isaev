package whz.pti.eva.praktikum_03.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransferDTO implements Serializable{

    String to;
    BigDecimal amount;

    public TransferDTO() {
    }

    public TransferDTO(String to, BigDecimal amount) {
        this.to = to;
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
