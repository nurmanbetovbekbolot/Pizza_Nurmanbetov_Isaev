package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Customer repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface CustomerRepository extends JpaRepository<Customer, String> {

    /**
     * Find customer by user id optional.
     *
     * @param userId the user id
     * @return the optional Customer
     */
    Optional<Customer> findCustomerByUserId(String userId);

}