package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Cart repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    /**
     * Find by customer id optional.
     *
     * @param customerId the customer id
     * @return the optional Cart
     */
    Optional<Cart> findByCustomerId(String customerId);
}
