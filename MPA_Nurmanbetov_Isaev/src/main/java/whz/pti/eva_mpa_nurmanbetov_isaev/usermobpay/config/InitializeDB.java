package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.config;

import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.PayUser;
import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.PayUserRepository;
import whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializeDB {

    private static final Logger log = LoggerFactory.getLogger(InitializeDB.class);

    @Autowired private PayUserRepository payUserRepository;

    @PostConstruct
    public void init()  {

            log.debug("Db initialized");

            PayUser payUser = new PayUser();
            payUser.setName("ps");
            payUser.setState(State.available);
            payUserRepository.save(payUser);

            payUser = new PayUser();
            payUser.setName("bnutz");
            payUser.setState(State.available); //doesNotExist);
            payUserRepository.save(payUser);

            payUser = new PayUser();
            payUser.setName("сnutz");
            payUser.setState(State.available); //requested
            payUserRepository.save(payUser);

//            payUser = new PayUser();
//            payUser.setName("agneta");
//            payUser.setState(State.suspended);
//            payUserRepository.save(payUser);

    }
}
