package whz.pti.eva.praktikum_03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import whz.pti.eva.praktikum_03.enums.Role;

@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String email;
    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;
    private String password;
    private String passwordRepeated;

    private Boolean isActive;
}
