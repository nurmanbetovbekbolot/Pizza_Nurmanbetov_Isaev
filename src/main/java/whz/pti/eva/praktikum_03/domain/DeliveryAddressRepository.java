package whz.pti.eva.praktikum_03.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    @Query(value = "SELECT DA.ID,DA.HOUSE_NUMBER,DA.POSTAL_CODE,DA.STREET,DA.TOWN FROM CUSTOMER_DELIVERY_ADDRESS CDA JOIN DELIVERY_ADDRESS DA ON CDA.DELIVERY_ADDRESS_ID = DA.ID WHERE DELIVERY_ADDRESS_ID=:D_ID", nativeQuery = true)
    Optional<DeliveryAddress> findDeliveryAddressById(@Param("D_ID") Long id);
}
