package whz.pti.eva.praktikum_03.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUser implements Serializable {

    private User user;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "getNickname=" + user.getNickname() +
                "} " + super.toString();
    }
}
