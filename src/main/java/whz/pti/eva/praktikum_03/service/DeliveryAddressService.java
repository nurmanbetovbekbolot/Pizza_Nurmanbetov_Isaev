package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;

public interface DeliveryAddressService {
    DeliveryAddress save (DeliveryAddress address);
    DeliveryAddress updateAddress(DeliveryAddressDTO deliveryAddressDTO);
    DeliveryAddressDTO findDeliveryAddressById(Long id);
    DeliveryAddress getDeliveryAddressById(Long id);
}
