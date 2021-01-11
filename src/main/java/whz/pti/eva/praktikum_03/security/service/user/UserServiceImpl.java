package whz.pti.eva.praktikum_03.security.service.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.DeliveryAddress;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.dto.PayActionResponseDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;
import whz.pti.eva.praktikum_03.service.*;

import java.util.*;

/**
 * The type User service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeliveryAddressService deliveryAddressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private PaymentService paymentService;


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
        log.debug("Checking user={}", loginName);

        return userRepository.existsByLoginName(loginName);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking user={}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.debug("Getting all users");
        List<User> targetListOrigin = userRepository.findAllByOrderByLoginNameAsc();
        List<UserDTO> targetList = new ArrayList<>();
        for (User source : targetListOrigin) {
            UserDTO target = new UserDTO();
            BeanUtils.copyProperties(source, target);
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
        log.debug("Creating user(registration)");
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User();
        user.setLoginName(form.getLoginName());
        user.setEmail(form.getEmail());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setActive(form.getActive());
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setLoginName(user.getLoginName());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        DeliveryAddress deliveryAddress = new DeliveryAddress(form.getStreet(), form.getHouseNumber(), form.getTown(), form.getPostalCode());
        deliveryAddressService.save(deliveryAddress);
        customer.getDeliveryAddress().add(deliveryAddress);
        customer.setActive(form.getActive());
        customer.setPasswordHash(user.getPasswordHash());
        customer.setUser(user);

        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setCustomer(customer);
            cartService.saveCart(newCart);
        } else {
            Map<String, Item> cartItems = cart.getItems();
            for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
                itemService.saveItem(entry.getValue());
            }
            cart.setCustomer(customer);
            cartService.saveCart(cart);
        }
        customerService.saveCustomer(customer);
        Optional<User> user1 = userRepository.findById(user.getId());

        PayActionResponseDTO payActionResponseDTO = paymentService.doPayAction(form.getLoginName(), "pizzaService", "open");


        return user1.orElse(new User());
    }

    @Override
    @Transactional
    public User createByAdmin(UserCreateForm form) {
        log.debug("Creating user(admin)");
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User();
        user.setLoginName(form.getLoginName());
        user.setEmail(form.getEmail());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setActive(form.getActive());
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setLoginName(user.getLoginName());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        DeliveryAddress deliveryAddress = new DeliveryAddress(form.getStreet(), form.getHouseNumber(), form.getTown(), form.getPostalCode());
        deliveryAddressService.save(deliveryAddress);
        customer.getDeliveryAddress().add(deliveryAddress);
        customer.setActive(form.getActive());
        customer.setPasswordHash(user.getPasswordHash());
        customer.setUser(user);

        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        cartService.saveCart(newCart);
        customerService.saveCustomer(customer);
        Optional<User> user1 = userRepository.findById(user.getId());
        PayActionResponseDTO payActionResponseDTO = paymentService.doPayAction(form.getLoginName(), "pizzaService", "open");

        return user1.orElse(new User());
    }

    @Override
    public User findUserByCustomerId(String id) {
        log.debug("Finding user{}", id);
        User user = userRepository.findUserByCustomerId(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> User=%s not found", id)));
        return user;
    }
}