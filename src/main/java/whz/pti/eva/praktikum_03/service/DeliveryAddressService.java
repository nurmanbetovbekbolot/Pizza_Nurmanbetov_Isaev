package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;

/**
 * The interface Delivery address service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface DeliveryAddressService {
    /**
     * Save delivery address.
     *
     * @param address the address
     * @return the delivery address
     */
    DeliveryAddress save (DeliveryAddress address);

    /**
     * Update address delivery address.
     *
     * @param deliveryAddressDTO the delivery address dto
     * @return the delivery address
     */
    DeliveryAddress updateAddress(DeliveryAddressDTO deliveryAddressDTO);

    /**
     * Find delivery address by id delivery address dto.
     *
     * @param id the id
     * @return the delivery address dto
     */
    DeliveryAddressDTO findDeliveryAddressById(Long id);

    /**
     * Gets delivery address by id.
     *
     * @param id the id
     * @return the delivery address by id
     */
    DeliveryAddress getDeliveryAddressById(Long id);
}
