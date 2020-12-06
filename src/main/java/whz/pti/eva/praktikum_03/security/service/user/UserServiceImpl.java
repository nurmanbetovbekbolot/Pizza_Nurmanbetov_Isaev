package whz.pti.eva.praktikum_03.security.service.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private CustomerRepository customerRepository;
    private DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
    }


    @Override
    public UserDTO getUserById(String id) {
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
    public User create(UserCreateForm form, Cart cart) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


        User user = new User();
        user.setLoginName(form.getLoginName());
        user.setEmail(form.getEmail());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setIsActive(form.getIsActive());
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setLoginName(user.getLoginName());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        DeliveryAddress deliveryAddress = new DeliveryAddress(form.getStreet(), form.getHouseNumber(), form.getTown(), form.getPostalCode());
        deliveryAddressRepository.save(deliveryAddress);
        customer.getDeliveryAddress().add(deliveryAddress);
        customer.setIsActive(form.getIsActive());
        customer.setPasswordHash(user.getPasswordHash());
        customer.setUser(user);

        if (cart == null)
        {
            Cart newCart = new Cart();
            newCart.setCustomer(customer);
            cartRepository.save(newCart);
        }
        else {
            Map<String, Item> cartItems = cart.getItems();
            for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
                itemRepository.save(entry.getValue());
            }
            cart.setCustomer(customer);
            cartRepository.save(cart);
        }
        customerRepository.save(customer);
        Optional<User> user1 = userRepository.findById(user.getId());
        return user1.orElse(new User());
    }

    @Override
    public void registration(Cart cart) {
//        if (cart == null)
//        {
//            Cart newCart = new Cart();
//            newCart.setCustomer(customer);
//            cartRepository.save(newCart);
//        }
//        else {
//            Map<String, Item> cartItems = cart.getItems();
//            for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
//                itemRepository.save(entry.getValue());
//            }
//            cart.setCustomer(customer);
//            cartRepository.save(cart);
//        }
    }

    @Override
    public User findUserByCustomerId(String id) {
        Optional<User> user = userRepository.findUserByCustomerId(id);
        return user.orElse(new User());
    }
}