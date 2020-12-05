package whz.pti.eva.praktikum_03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    private Boolean isActive;
}
