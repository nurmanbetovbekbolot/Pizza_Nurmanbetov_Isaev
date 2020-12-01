package whz.pti.eva.praktikum_03.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import whz.pti.eva.praktikum_03.enums.Role;

@Getter
@Setter
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user){
        super(user.getLoginName(), user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public String getLoginName() {
        return user.getLoginName();}

    public User getUser() {
        return user;
    }

    public Long getId(){
        return user.getId();
    }

    public Role getRole(){
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "getNickname=" + user.getLoginName() +
                "} " + super.toString();
    }
}
