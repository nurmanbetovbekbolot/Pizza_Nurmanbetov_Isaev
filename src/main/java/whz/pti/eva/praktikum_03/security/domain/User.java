package whz.pti.eva.praktikum_03.security.domain;

import lombok.*;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "secuser")
public class User extends BaseEntity<Long> {

	@Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                "nicknameo=" + nickname +
                ", email='" + email.replaceFirst("@.*", "@***") +
                ", password='" + password.substring(0, 10) +
                ", role=" + role +
                '}';
    }
}
