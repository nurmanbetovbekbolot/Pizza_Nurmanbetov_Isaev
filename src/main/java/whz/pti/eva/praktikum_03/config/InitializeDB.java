package whz.pti.eva.praktikum_03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Initialize db.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Component
public class InitializeDB {

    /**
     * The Cart repository.
     */
    @Autowired
    CartRepository cartRepository;

    /**
     * The Item repository.
     */
    @Autowired
    ItemRepository itemRepository;

    /**
     * The Ordered repository.
     */
    @Autowired
    OrderedRepository orderedRepository;

    /**
     * The Ordered item repository.
     */
    @Autowired
    OrderedItemRepository orderedItemRepository;

    /**
     * The Pizza repository.
     */
    @Autowired
    PizzaRepository pizzaRepository;

    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * The Delivery address repository.
     */
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    /**
     * The Customer repository.
     */
    @Autowired
    CustomerRepository customerRepository;


    /**
     * Init.
     */
    @Transactional
    @PostConstruct
    public void init() {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Pizza pizzaMargherita = new Pizza();
        pizzaMargherita.setName("Margherita");
        pizzaMargherita.setPriceSmall(new BigDecimal("5.0"));
        pizzaMargherita.setPriceMedium(new BigDecimal("8.0"));
        pizzaMargherita.setPriceLarge(new BigDecimal("10.0"));
        pizzaRepository.save(pizzaMargherita);

        Pizza pizzaHawai = new Pizza();
        pizzaHawai.setName("Hawai");
        pizzaHawai.setPriceSmall(new BigDecimal("7.0"));
        pizzaHawai.setPriceMedium(new BigDecimal("9.0"));
        pizzaHawai.setPriceLarge(new BigDecimal("12.0"));
        pizzaRepository.save(pizzaHawai);

        Pizza pizzaTonno = new Pizza();
        pizzaTonno.setName("Tonno");
        pizzaTonno.setPriceSmall(new BigDecimal("6.0"));
        pizzaTonno.setPriceMedium(new BigDecimal("10.0"));
        pizzaTonno.setPriceLarge(new BigDecimal("13.0"));
        pizzaRepository.save(pizzaTonno);

        User user = new User();
        user.setLoginName("admin");
        user.setEmail("admin@gmail.com");
        user.setPasswordHash(passwordEncoder.encode("a1"));
        user.setRole(Role.ADMIN);
        user.setActive(true);
        userRepository.save(user);

        User user1 = new User();
        user1.setLoginName("bnutz");
        user1.setEmail("bnutz@gmail.com");
        user1.setPasswordHash(passwordEncoder.encode("n1"));
        user1.setRole(Role.USER);
        user1.setActive(true);
        userRepository.save(user1);

        Customer customer1 = new Customer();
        customer1.setLoginName(user1.getLoginName());
        customer1.setFirstName("John");
        customer1.setLastName("Smith");
        DeliveryAddress deliveryAddress = new DeliveryAddress("Innere Schneeberger Str", "23", "Zwickau", "08056");
        DeliveryAddress deliveryAddress2 = new DeliveryAddress("Schneeberger Str", "25", "Zwickau", "08056");
        deliveryAddressRepository.save(deliveryAddress);
        deliveryAddressRepository.save(deliveryAddress2);
        List<DeliveryAddress> addressList = new ArrayList<>();
        addressList.add(deliveryAddress);
        addressList.add(deliveryAddress2);
        customer1.setDeliveryAddress(addressList);
        customer1.setActive(true);
        customer1.setPasswordHash(user1.getPasswordHash());
        customer1.setUser(user1);
        customerRepository.save(customer1);

        Cart cart = new Cart();
        cart.setCustomer(customer1);
        cartRepository.save(cart);

        User user2 = new User();
        user2.setLoginName("cnutz");
        user2.setEmail("cnutz@gmail.com");
        user2.setPasswordHash(passwordEncoder.encode("n2"));
        user2.setRole(Role.USER);
        user2.setActive(true);
        userRepository.save(user2);
        Customer customer = new Customer();
        customer.setLoginName(user2.getLoginName());
        customer.setFirstName("Tom");
        customer.setLastName("Soyer");
        DeliveryAddress deliveryAddress1 = new DeliveryAddress("Kepler Str", "7", "Zwickau", "08056");
        deliveryAddressRepository.save(deliveryAddress1);
        customer.getDeliveryAddress().add(deliveryAddress1);
        customer.setActive(true);
        customer.setPasswordHash(user2.getPasswordHash());
        customer.setUser(user2);
        customerRepository.save(customer);

        Cart cart1 = new Cart();
        cart1.setCustomer(customer);
        cartRepository.save(cart1);
    }
}