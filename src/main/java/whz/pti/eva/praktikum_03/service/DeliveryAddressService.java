package whz.pti.eva.praktikum_03.service;

import org.springframework.data.repository.query.Param;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressService {
    void save (DeliveryAddress address);
    DeliveryAddress updateAddress(DeliveryAddressDTO deliveryAddressDTO);
    DeliveryAddressDTO findDeliveryAddressById(Long id);
    DeliveryAddress getDeliveryAddressById(Long id);

//    List<DeliveryAddress> getDeliveryAddressesByCustomer(String customerId);
//
//    DeliveryAddress getOneDeliveryAddressByCustomer(String customerId);

}
