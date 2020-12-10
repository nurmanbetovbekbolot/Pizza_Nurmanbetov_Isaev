package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Ordered repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface OrderedRepository extends JpaRepository<Ordered, Long> {
    /**
     * Find by user id optional.
     *
     * @param customer the customer
     * @return the optional Ordered
     */
    Optional<Ordered> findByUserId(Customer customer);

    /**
     * Find all orders by user id.
     *
     * @param userId the user id
     * @return the list Ordered
     */
    List<Ordered> findAllByUserId(Customer userId);

}
