package whz.pti.eva.praktikum_03.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whz.pti.eva.praktikum_03.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByLoginName(String loginName);
    boolean existsByEmail(String email);
    boolean existsByLoginName(String loginName);
    @Query(value = "select sc.id,sc.login_name,sc.password_hash,sc.is_active from secuser sc inner join customer c on sc.id = c.user_id where c.id = :cus_id",nativeQuery = true)
    Optional<User>  findUserByCustomerId(@Param("cus_id")String id);

//    @Query(value = "select * from secuser where id = :secuser_id",nativeQuery = true)
//    Optional<User> findById(@Param("secuser_id") String id);


    List<User> findAllByOrderByLoginNameAsc();
}