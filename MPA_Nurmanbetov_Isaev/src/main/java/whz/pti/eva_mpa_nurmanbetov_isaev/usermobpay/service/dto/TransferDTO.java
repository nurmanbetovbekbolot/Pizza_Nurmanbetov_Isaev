package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.service.dto;

public class TransferDTO {

    String to;
    int amount;

    public TransferDTO() {
    }

    public TransferDTO(String to, int amount) {
        this.to = to;
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }
}
