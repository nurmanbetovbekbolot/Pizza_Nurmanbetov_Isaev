package whz.pti.eva.praktikum_03.security.service.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.CustomerRepository;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.DeliveryAddressRepository;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private CustomerRepository customerRepository;
    private DeliveryAddressRepository deliveryAddressRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }


    @Override
    public UserDTO getUserById(long id) {
        log.debug("Getting user={}", id);
        User user = userRepository.findById(id).orElseThrow(() ->
		new NoSuchElementException(String.format(">>> User=%s not found", id)));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    
    @Override
    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public boolean existByLoginName(String loginName) {
        return userRepository.existsByLoginName(loginName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.debug("Getting all users");
        List<User> targetListOrigin = userRepository.findAllByOrderByLoginNameAsc();
		List<UserDTO> targetList= new ArrayList<>(); 
		for (User source: targetListOrigin ) {
		  	 UserDTO target= new UserDTO();
		     BeanUtils.copyProperties(source , target);
		     targetList.add(target);
		}
        return targetList;
    }

    @Override
    public Optional<User> getUserByLoginName(String loginName) {
        log.debug("Getting user by loginName");
        return userRepository.findOneByLoginName(loginName);
    }

    @Override
    @Transactional
    public User create(UserCreateForm form) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Customer customer = new Customer();
        customer.setIsActive(Boolean.TRUE);
        customer.setLoginName(form.getLoginName());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        DeliveryAddress deliveryAddress = new DeliveryAddress(form.getStreet(), form.getHouseNumber(), form.getTown(), form.getPostalCode());
        List<DeliveryAddress> deliveryAddresses = new ArrayList<>();
        deliveryAddresses.add(deliveryAddress);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customer.setDeliveryAddress(deliveryAddresses);
        deliveryAddress.setCustomer(customers);



        User user = new User();
        user.setEmail(form.getEmail());
        user.setLoginName(form.getLoginName());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole());

        customer.setUser(user);

        customerRepository.save(customer);
        deliveryAddressRepository.save(deliveryAddress);

        user.setIsActive(form.getIsActive() != null);
        return userRepository.save(user);
    }

}
