package whz.pti.eva.praktikum_03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.DeliveryAddressRepository;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;
import whz.pti.eva.praktikum_03.security.service.user.UserServiceImpl;

import java.util.NoSuchElementException;

/**
 * The class DeliveryAddressServiceImpl for middleware. All business logic is here
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private DeliveryAddressRepository deliveryAddressRepository;


    /**
     * Instantiates a new Delivery address service.
     *
     * @param deliveryAddressRepository the delivery address repository
     */
    @Autowired
    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Long id) {
        log.debug("Getting delivery address by id={}", id);
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findDeliveryAddressById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> DeliveryAddress not found with id=%s", id)));
        return deliveryAddress;
    }

    @Override
    public DeliveryAddressDTO findDeliveryAddressById(Long id) {
        log.debug("Finding delivery address by id={}", id);
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
        log.debug("Updating delivery address");
        DeliveryAddress deliveryAddress = getDeliveryAddressById(deliveryAddressDTO.getId());
        deliveryAddress.setStreet(deliveryAddressDTO.getStreet());
        deliveryAddress.setHouseNumber(deliveryAddressDTO.getHouseNumber());
        deliveryAddress.setTown(deliveryAddressDTO.getTown());
        deliveryAddress.setPostalCode(deliveryAddressDTO.getPostalCode());
        return deliveryAddressRepository.save(deliveryAddress);
    }

    @Override
    public DeliveryAddress save(DeliveryAddress address) {
        log.debug("Saving delivery address");
        return deliveryAddressRepository.save(address);
    }
}
