package whz.pti.eva.praktikum_03.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.eva.praktikum_03.enums.Role;

import javax.validation.constraints.NotEmpty;

/**
 * The class UserCreateform. It is a model of user to get from user_create view
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateForm {

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @NotEmpty
    private String loginName = "";

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String street = "";

    @NotEmpty
    private String houseNumber = "";

    @NotEmpty
    private String town = "";

    @NotEmpty
    private String postalCode = "";

    @NotEmpty
    private String password = "";

    @NotEmpty()
    private String passwordRepeated = "";

    private Boolean isActive = Boolean.TRUE;

    private Role role = Role.USER;

    @Override
    public String toString() {
        return "UserCreateForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", town='" + town + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", password=***'" + password + '\'' +
                ", passwordRepeated=***'" + passwordRepeated + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                '}';
    }


}
