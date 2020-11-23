package whz.pti.eva.praktikum_03.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.eva.praktikum_03.enums.Role;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateForm {

    @NotEmpty
    private String nickname = "";

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty()
    private String passwordRepeated = "";

    private Boolean isActive;

//    @NotNull
    private Role role = Role.USER;

    @Override
    public String toString() {
        return "UserCreateForm{" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                ", role=" + role +
                ", nickname=" + nickname +
                '}';

    }


}
