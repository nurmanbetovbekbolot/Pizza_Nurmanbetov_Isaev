package whz.pti.eva.praktikum_03.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long id;
    private String nickname;
    private String email;

}
