package whz.pti.eva.praktikum_03.security.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.eva.praktikum_03.common.BaseEntity;
import whz.pti.eva.praktikum_03.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The class User. This class is for security with according attributes
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "secuser")
public class User extends BaseEntity<String> {

    @Column(name = "loginName", nullable = false, unique = true)
    private String loginName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active")
    private Boolean isActive;

    public String getId() {
        return super.getId();
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets login name.
     *
     * @return the login name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets login name.
     *
     * @param loginName the login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Gets password hash.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets password hash.
     *
     * @param password the password
     */
    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                "loginName=" + loginName +
                ", email='" + email.replaceFirst("@.*", "@***") +
                ", password='" + passwordHash.substring(0, 10) +
                ", role=" + role +
                '}';
    }
}