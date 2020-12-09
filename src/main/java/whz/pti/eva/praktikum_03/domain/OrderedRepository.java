package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface OrderedRepository extends JpaRepository<Ordered,Long> {
    Optional<Ordered> findByUserId(Customer customer);

//    List<Ordered> findAllByUserId(Customer customer);

}
