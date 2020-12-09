package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Delivery address repository.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    /**
     * Find delivery address by id optional.
     *
     * @param id the id
     * @return the optional DeliveryAddress
     */
    Optional<DeliveryAddress> findDeliveryAddressById(Long id);
}
