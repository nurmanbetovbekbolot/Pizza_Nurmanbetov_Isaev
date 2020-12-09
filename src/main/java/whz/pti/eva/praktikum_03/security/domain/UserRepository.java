package whz.pti.eva.praktikum_03.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface UserRepository extends JpaRepository<User, String> {


    /**
     * Find one by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findOneByEmail(String email);

    /**
     * Find one by login name optional.
     *
     * @param loginName the login name
     * @return the optional
     */
    Optional<User> findOneByLoginName(String loginName);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);

    /**
     * Exists by login name boolean.
     *
     * @param loginName the login name
     * @return the boolean
     */
    boolean existsByLoginName(String loginName);

    /**
     * Find user by customer id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Query(value = "select sc.id,sc.login_name,sc.password_hash,sc.is_active from secuser sc inner join customer c on sc.id = c.user_id where c.id = :cus_id",nativeQuery = true)
    Optional<User>  findUserByCustomerId(@Param("cus_id")String id);

    /**
     * Find all by order by login name asc list.
     *
     * @return the list
     */
    List<User> findAllByOrderByLoginNameAsc();
}