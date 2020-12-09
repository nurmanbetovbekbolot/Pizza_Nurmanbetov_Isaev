package whz.pti.eva.praktikum_03.security.service.user;


import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    UserDTO getUserById(String id);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByLoginName(String loginName);
    boolean existByLoginName(String loginName);
    boolean existsByEmail(String email);
    Collection<UserDTO> getAllUsers();
    User create(UserCreateForm form, Cart cart);
    User createByAdmin(UserCreateForm form);
    User  findUserByCustomerId(String id);

}