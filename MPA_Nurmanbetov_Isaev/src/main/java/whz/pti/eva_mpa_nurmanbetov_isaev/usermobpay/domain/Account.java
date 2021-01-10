package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import whz.pti.eva_mpa_nurmanbetov_isaev.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Account extends BaseEntity<Long> {

    private BigDecimal balance;

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private PayUser payUser;

    public Account() {
        this.balance = new BigDecimal("100.00");
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public PayUser getPayUser() {
        return payUser;
    }

    public void setPayUser(PayUser payUser) {
        this.payUser = payUser;
    }

    public void depositBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdrawBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}