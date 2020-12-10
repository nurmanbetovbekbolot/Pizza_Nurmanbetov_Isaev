package whz.pti.eva.praktikum_03.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import whz.pti.eva.praktikum_03.enums.Role;

/**
 * The class Currentuser. This class is mainly responsible for authenticating user
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    /**
     * Instantiates a new Current user.
     *
     * @param user the user
     */
    public CurrentUser(User user) {
        super(user.getLoginName(), user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    /**
     * Gets login name.
     *
     * @return the login name
     */
    public String getLoginName() {
        return user.getLoginName();
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Get id string.
     *
     * @return the string
     */
    public String getId() {
        return user.getId();
    }

    /**
     * Get role role.
     *
     * @return the role
     */
    public Role getRole() {
        return user.getRole();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getActive();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "getNickname=" + user.getLoginName() +
                "} " + super.toString();
    }
}