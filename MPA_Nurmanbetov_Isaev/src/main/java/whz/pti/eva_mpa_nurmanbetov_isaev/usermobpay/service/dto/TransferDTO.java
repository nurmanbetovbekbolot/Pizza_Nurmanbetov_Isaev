package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.service.dto;

import java.math.BigDecimal;

public class TransferDTO {

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
