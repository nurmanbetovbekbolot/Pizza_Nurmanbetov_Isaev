package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.CustomerRepository;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.service.user.UserService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DeliveryAddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerDTO findByUserId(String userId) {
        Customer customer = customerRepository.findCustomerByUserId(userId).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> Customer not found with userId=%s", userId)));
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        customerDTO.setId(customer.getId());
        customerDTO.setDeliveryAddresses(customer.getDeliveryAddress());
        customerDTO.setUser(customer.getUser().getId());
        return customerDTO;
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Optional<Customer> tempCustomer = customerRepository.findById(customerDTO.getId());
        User user = userService.findUserByCustomerId(customerDTO.getId());
        if (tempCustomer.isPresent()) {
            Customer customer = tempCustomer.get();
            if (customerDTO.getPasswordHash() != null && customerDTO.getPasswordHash().length() >= 5) {
                customer.setPasswordHash(passwordEncoder.encode(customerDTO.getPasswordHash()));
                user.setPasswordHash(passwordEncoder.encode(customerDTO.getPasswordHash()));
            }
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setLoginName(customerDTO.getLoginName());
            user.setLoginName(customerDTO.getLoginName());
            if (customerDTO.getIsActive() == null) {
                customer.setIsActive(Boolean.FALSE);
                user.setIsActive(Boolean.FALSE);
            } else {
                customer.setIsActive(customerDTO.getIsActive());
                user.setIsActive(customerDTO.getIsActive());
            }
//            DeliveryAddress deliveryAddress = new DeliveryAddress(customerDTO.getStreet(),customerDTO.getHouseNumber(),customerDTO.getPostalCode(),customerDTO.getTown());
            customer.setDeliveryAddress(customerDTO.getDeliveryAddresses());
//            addressService.save(deliveryAddress);
            return customerRepository.save(customer);
        } else
            throw new NoSuchElementException(String.format(">>> Customer=%s not found", customerDTO.getId()));
    }

    @Override
    public Customer getCustomerById(String customerId) {
         Optional<Customer> customer = customerRepository.findById(customerId);
         return customer.orElse(new Customer());
    }
}
