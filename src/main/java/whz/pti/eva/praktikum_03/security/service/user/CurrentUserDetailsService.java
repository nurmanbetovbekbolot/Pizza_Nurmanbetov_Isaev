package whz.pti.eva.praktikum_03.security.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.security.boundary.UserController;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;

/**
 * The type Current user details service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    /**
     * Instantiates a new Current user details service.
     *
     * @param userService the user service
     */
    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String loginName)

            throws UsernameNotFoundException {
        log.info("Loading user by login Name");
        User user =
                userService.getUserByLoginName(loginName)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User with loginName= " + loginName +
                                        " cannot be not found"));
        return new CurrentUser(user);
    }

}
