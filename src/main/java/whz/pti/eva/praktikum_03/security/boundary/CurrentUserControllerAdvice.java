package whz.pti.eva.praktikum_03.security.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.dto.PayActionResponseDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.PaymentService;

import java.math.BigDecimal;

/**
 * The class CurrentUserControllerAdvice mainly responsible to provide currentUser everywhere where it is needed.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    PaymentService paymentService;

    /**
     * Gets current user by authenticating it
     *
     * @param authentication the authentication
     * @return the current user
     */
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
        log.info("Getting currentUser");
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }

    @ModelAttribute("kontoBestand")
    public BigDecimal getKonto(Authentication authentication) {
        CurrentUser currentCustomer = getCurrentUser(authentication);
        log.info("Getting currentUser");
        if (currentCustomer!=null && currentCustomer.getRole()!= Role.ADMIN) {
            PayActionResponseDTO payActionResponseDTO = paymentService.doPayAction(currentCustomer.getLoginName(), "pizzaService", "get");
        BigDecimal clientBalance = new BigDecimal(payActionResponseDTO.getDescription());
        return clientBalance;}
    return null;
    }

}
