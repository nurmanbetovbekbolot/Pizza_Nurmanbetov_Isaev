package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.DeliveryAddressRepository;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;

import java.util.NoSuchElementException;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
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
    public DeliveryAddress save(DeliveryAddress address) {
        return deliveryAddressRepository.save(address);
    }
}
