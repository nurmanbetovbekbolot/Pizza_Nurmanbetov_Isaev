package whz.pti.eva.praktikum_03.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByLoginName(String loginName);
    boolean existsByEmail(String email);
    boolean existsByLoginName(String loginName);

    User findById(String id);

    List<User> findAllByOrderByLoginNameAsc();
}