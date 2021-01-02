package whz.pti.eva_mpa_nurmanbetov_isaev.usermobpay.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RepositoryRestResource
public interface PayUserRepository extends JpaRepository<PayUser, Integer> {
    Optional<PayUser> findByName(String name);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PayUser c WHERE c.name = ?1 and c.state = 'available'")
    boolean existsByNameAndAvailable(String name);

}
