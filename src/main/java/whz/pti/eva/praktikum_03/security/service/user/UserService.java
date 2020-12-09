package whz.pti.eva.praktikum_03.security.service.user;


import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

/**
 * The interface User service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface UserService {

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    UserDTO getUserById(String id);

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Gets user by loginName.
     *
     * @param loginName the login name
     * @return the user by login name
     */
    Optional<User> getUserByLoginName(String loginName);

    /**
     * Exist by loginName boolean.
     *
     * @param loginName the login name
     * @return the boolean
     */
    boolean existByLoginName(String loginName);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    Collection<UserDTO> getAllUsers();

    /**
     * Create user.
     *
     * @param form the form
     * @param cart the cart
     * @return the user
     */
    User create(UserCreateForm form, Cart cart);

    /**
     * Create by admin user.
     *
     * @param form the form
     * @return the user
     */
    User createByAdmin(UserCreateForm form);

    /**
     * Find user by customer id user.
     *
     * @param id the id
     * @return the user
     */
    User  findUserByCustomerId(String id);

}