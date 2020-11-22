package whz.pti.eva.praktikum_03.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    
    List<User> findAllByOrderByNicknameAsc();
}
