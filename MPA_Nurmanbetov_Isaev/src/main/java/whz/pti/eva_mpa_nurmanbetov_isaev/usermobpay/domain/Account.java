package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import whz.pti.eva_mpa_nurmanbetov_isaev.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Account extends BaseEntity<Long> {

    private int balance;

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private PayUser payUser;

    public Account() {
        this.balance = 100;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public PayUser getPayUser() {
        return payUser;
    }

    public void setPayUser(PayUser payUser) {
        this.payUser = payUser;
    }

    public void depositBalance(int amount) {
        this.balance += amount;
    }

    public void withdrawBalance(int amount) {
        this.balance -= amount;
    }
}