package whz.pti.eva.praktikum_03.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.DeliveryAddressRepository;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Long id) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findDeliveryAddressById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> DeliveryAddress not found with id=%s", id)));
        return deliveryAddress;
    }

    @Override
    public DeliveryAddressDTO findDeliveryAddressById(Long id) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findDeliveryAddressById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> DeliveryAddress not found with id=%s", id)));
        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryAddressDTO.setId(deliveryAddress.getId());
        deliveryAddressDTO.setStreet(deliveryAddress.getStreet());
        deliveryAddressDTO.setHouseNumber(deliveryAddress.getHouseNumber());
        deliveryAddressDTO.setTown(deliveryAddress.getTown());
        deliveryAddressDTO.setPostalCode(deliveryAddress.getPostalCode());
        return deliveryAddressDTO;
    }

    @Override
    public DeliveryAddress updateAddress(DeliveryAddressDTO deliveryAddressDTO) {
        DeliveryAddress deliveryAddress = getDeliveryAddressById(deliveryAddressDTO.getId());
        deliveryAddress.setStreet(deliveryAddressDTO.getStreet());
        deliveryAddress.setHouseNumber(deliveryAddressDTO.getHouseNumber());
        deliveryAddress.setTown(deliveryAddressDTO.getTown());
        deliveryAddress.setPostalCode(deliveryAddressDTO.getPostalCode());
        return deliveryAddressRepository.save(deliveryAddress);
    }

    @Override
    public void save(DeliveryAddress address) {
        deliveryAddressRepository.save(address);
    }

//    @Override
//    public List<DeliveryAddress> getDeliveryAddressesByCustomer(String customerId) {
//        return deliveryAddressRepository.findDeliveryAddressesByCustomer(customerId);
//    }
//
//    @Override
//    public DeliveryAddress getOneDeliveryAddressByCustomer(String customerId) {
//        Optional<DeliveryAddress> deliveryAddress = deliveryAddressRepository.findDeliveryAddressByCustomer(customerId);
//        return deliveryAddress.orElse(new DeliveryAddress());
//    }
}
