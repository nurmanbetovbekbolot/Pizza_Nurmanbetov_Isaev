package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.service;

import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.PayUser;
import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.PayUserRepository;
import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PayUserServiceImpl implements PayUserService {

    private static final Logger log = LoggerFactory.getLogger(PayUserServiceImpl.class);

    @Autowired
    private PayUserRepository payUserRepository;

    @Override
    public BigDecimal getAccountBalanceByName(String userId) {
        PayUser payUser = null;
        try {
            payUser = payUserRepository.findByName(userId).orElseThrow(() -> new PayUserException("user cannot be found"));
        } catch (PayUserException e) {
            e.printStackTrace();
            return new BigDecimal("-101010");
        }
        return payUser.getAccount().getBalance();
    }

    @Override
    public boolean containsAndAvailable(String userId) {
        if (payUserRepository.existsByNameAndAvailable(userId)) return true;
        return false;
    }

    @Override
    public State getState(String userId) {
        Optional<PayUser> userPayOptional = payUserRepository.findByName(userId);
        if (userPayOptional.isPresent()) {
            // value is present inside Optional
            return userPayOptional.get().getState();

        } else {
            // value is absent
            return State.doesNotExist;
        }
    }

    @Override
    public void openAccount(String userId) {
        PayUser payUser = payUserRepository.findByName(userId).orElse(new PayUser(userId));
        payUser.setState(State.available);
        payUserRepository.save(payUser);
    }

    @Override
    @Transactional
    public String transfer(String from, String to, BigDecimal amount) throws PayUserException {
//    	try {
        if (containsAndAvailable(from) && containsAndAvailable(to)) {
            PayUser payUserFrom = payUserRepository.findByName(from).orElseThrow(() -> new PayUserException("user cannot be found"));
            payUserFrom.getAccount().withdrawBalance(amount);

            PayUser payUserTo = payUserRepository.findByName(to).orElseThrow(() -> new PayUserException("user cannot be found"));
            payUserTo.getAccount().depositBalance(amount);
            payUserRepository.save(payUserFrom);
//            throw new RuntimeException();
//            throw new PayUserException("au weia");
          payUserRepository.save(payUserTo);
          return "okay";
        } 
        else return "transferNotAllowed";
//          } catch (Exception e) {
//          TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
//          status.setRollbackOnly();
//          log.info("Transaction as rollback marked ? " + status.isRollbackOnly());
//      }
//		return null;
    }

    @Override
    public boolean deleteUser(String userId) {
        Optional<PayUser> userPayOptional = payUserRepository.findByName(userId);
        if (userPayOptional.isPresent()) {
            PayUser payUser = userPayOptional.get();
            payUser.setState(State.doesNotExist);
            payUser.getAccount().setBalance(new BigDecimal("0.00"));
            payUserRepository.save(payUser);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void changeUserToSuspendedState(String userId) {
        payUserRepository.findByName(userId).ifPresent((userPay) -> {
            userPay.setState(State.suspended);
            payUserRepository.save(userPay);
        });
    }

    @Override
    public PayUser getPayUser(String userId) {
        Optional<PayUser> userPayOptional = payUserRepository.findByName(userId);
        if (userPayOptional.isPresent()) {
            PayUser payUser = userPayOptional.get();
                return payUser;
            };
        return null;
    }
}
