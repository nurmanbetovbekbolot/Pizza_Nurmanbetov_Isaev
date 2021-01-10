package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.service;

import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.PayUser;
import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.State;

import java.math.BigDecimal;

public interface PayUserService {
    BigDecimal getAccountBalanceByName(String userId);

    boolean containsAndAvailable(String userId);

    State getState(String userId);

    void openAccount(String userId);

    String transfer(String from, String to, BigDecimal amount) throws PayUserException;

    boolean deleteUser(String userId);

    void changeUserToSuspendedState(String userId);

    PayUser getPayUser(String userId);
}
