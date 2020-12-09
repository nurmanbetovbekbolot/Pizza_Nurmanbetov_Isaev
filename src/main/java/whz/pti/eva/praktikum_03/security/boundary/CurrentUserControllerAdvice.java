package whz.pti.eva.praktikum_03.security.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;

/**
 * The class CurrentUserControllerAdvice mainly responsible to provide currentUser everywhere where it is needed.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

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
}
