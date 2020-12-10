package whz.pti.eva.praktikum_03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The class User dto.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Getter
@Setter
@ToString
public class UserDTO {

    private String id;
    private String loginName;
    private String email;
    private Boolean active;


}
