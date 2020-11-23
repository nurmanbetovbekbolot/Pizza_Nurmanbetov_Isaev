package whz.pti.eva.praktikum_03.security.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user =
                userService.getUserByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User with email= " + email +
                                        " cannot be not found"));
        return new CurrentUser(user);
    }

}
