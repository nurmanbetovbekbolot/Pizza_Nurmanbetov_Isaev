package whz.pti.eva.praktikum_03.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeDB {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    OrderedItemRepository orderedItemRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    CustomerRepository customerRepository;


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
        user.setLoginName("Admin");
        user.setEmail("admin@gmail.com");
        user.setPasswordHash(passwordEncoder.encode("admin"));
        user.setRole(Role.ADMIN);
        user.setIsActive(true);
        userRepository.save(user);

        User user1 = new User();
        user1.setLoginName("Nutzer1");
        user1.setEmail("bnutz");
        user1.setPasswordHash(passwordEncoder.encode("n1"));
        user1.setRole(Role.USER);
        user1.setIsActive(true);
        userRepository.save(user1);

        Customer customer1 = new Customer();
        customer1.setLoginName(user1.getLoginName());
        customer1.setFirstName("John");
        customer1.setLastName("Smith");
        DeliveryAddress deliveryAddress = new DeliveryAddress("","","","");
        deliveryAddress = new DeliveryAddress("","","","");
        deliveryAddressRepository.save(deliveryAddress);
        customer1.getDeliveryAddress().add(deliveryAddress);
        customer1.setIsActive(true);
        customer1.setPasswordHash(user1.getPasswordHash());
        customer1.setUser(user1);
        customerRepository.save(customer1);

        Cart cart = new Cart();
        cart.setCustomer(customer1);
        cartRepository.save(cart);

        User user2 = new User();
        user2.setLoginName("Nutzer2");
        user2.setEmail("cnutz");
        user2.setPasswordHash(passwordEncoder.encode("n2"));
        user2.setRole(Role.USER);
        user2.setIsActive(true);
        userRepository.save(user2);
        Customer customer = new Customer();
        customer.setLoginName(user2.getLoginName());
        customer.setFirstName("Tom");
        customer.setLastName("Soyer");
        deliveryAddressRepository.save(deliveryAddress);
        customer.getDeliveryAddress().add(deliveryAddress);
        customer.setIsActive(true);
        customer.setPasswordHash(user2.getPasswordHash());
        customer.setUser(user2);
        customerRepository.save(customer);

        cart.setCustomer(customer);
        cartRepository.save(cart);









//
//        OrderedItem orderedItem = new OrderedItem();
//        orderedItem.setName(pizzaHawai.getName());
//        orderedItem.setPizzaId(pizzaHawai.getId());
//        orderedItem.setQuantity(5);
//        orderedItem.setUserId("Atai");
//        orderedItemRepository.save(orderedItem);
//
//        OrderedItem orderedItem2 = new OrderedItem();
//        orderedItem2.setName(pizzaMargherita.getName());
//        orderedItem2.setPizzaId(pizzaMargherita.getId());
//        orderedItem2.setQuantity(2);
//        orderedItem2.setUserId("Atai");
//        orderedItemRepository.save(orderedItem2);
//
//        ArrayList<OrderedItem> orderedItems = new ArrayList<>();
//        orderedItems.add(orderedItem);
//        orderedItems.add(orderedItem2);
//
//        Ordered order1 = new Ordered();
//        order1.setItems(orderedItems);
//        order1.setNumberOfItems(10);
//        orderedRepository.save(order1);
//
//        Cart cart = new Cart();
//        cart.setQuantity(5);
//        cart.setUserId("ATAI1");
    }
}