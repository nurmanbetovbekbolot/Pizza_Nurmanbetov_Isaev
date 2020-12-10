package whz.pti.eva.praktikum_03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.CustomerRepository;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.security.service.user.UserServiceImpl;

import java.util.NoSuchElementException;

/**
 * The class CustomerServiceImpl for middleware. All business logic is here
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     * Field injection
     */
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DeliveryAddressService addressService;

    @Override
    public CustomerDTO findByUserId(String userId) {
        log.debug("Finding customer by userId={}", userId);

        Customer customer = customerRepository.findCustomerByUserId(userId).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> Customer not found with userId=%s", userId)));
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        customerDTO.setId(customer.getId());
        customerDTO.setDeliveryAddresses(customer.getDeliveryAddress());
        customerDTO.setUser(customer.getUser().getId());
        return customerDTO;
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO) {
        log.debug("Updating customer");

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Customer customer = getCustomerById(customerDTO.getId());
        User user = userService.findUserByCustomerId(customerDTO.getId());
        if (customerDTO.getPasswordHash() != null && customerDTO.getPasswordHash().length() >= 5) {
            customer.setPasswordHash(passwordEncoder.encode(customerDTO.getPasswordHash()));
            user.setPasswordHash(passwordEncoder.encode(customerDTO.getPasswordHash()));
        }
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setLoginName(customerDTO.getLoginName());
        user.setLoginName(customerDTO.getLoginName());
        if (customerDTO.getActive() == null) {
            customer.setActive(Boolean.FALSE);
            user.setActive(Boolean.FALSE);
        } else {
            customer.setActive(customerDTO.getActive());
            user.setActive(customerDTO.getActive());
        }
        customer.setDeliveryAddress(customerDTO.getDeliveryAddresses());
        return customerRepository.save(customer);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        log.debug("Saving customer");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        log.debug("Getting customer By id={}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> Customer not found with id=%s", customerId)));
        return customer;
    }
}
