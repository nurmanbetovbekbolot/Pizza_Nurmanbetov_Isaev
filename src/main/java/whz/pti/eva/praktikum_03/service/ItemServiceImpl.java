package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;



    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;



    @Override
    public List<Item> listAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public boolean addItem(Item item) {
        if (item == null) return false;

        itemRepository.save(item);
        return true;
    }


    //For LoggedIn
    @Override
    public void addItem(PizzaSize pizzaSize, Integer quantity, String pizzaName, CartDTO cartDTO, CustomerDTO customerDTO) {
        Item item = new Item();
        Pizza pizzaInDb = pizzaService.findPizzaByName(pizzaName);
        item.setQuantity(quantity);
        item.setPizza(pizzaInDb);
        item.setPizzaSize(pizzaSize);
        itemRepository.save(item);
        cartDTO.getItems().put(item.getId(), item);
        cartDTO.increment();
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO,cart);
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        cart.setCustomer(customer);
        cartService.saveCart(cart);
    }

    //For NotLogedIn
    @Override
    public void addItem(PizzaSize pizzaSize, Integer quantity, String pizzaName, CartDTO cart) {
        Item item = new Item();
        Pizza pizzaInDb = pizzaService.findPizzaByName(pizzaName);
        item.setQuantity(quantity);
        item.setPizza(pizzaInDb);
        item.setPizzaSize(pizzaSize);
        cart.getItems().put(UUID.randomUUID().toString(), item);
        cart.increment();
    }

    //Migrate all Items from sessions Cart
    @Override
    public void updateCustomersCart(CartDTO cart, CustomerDTO customer) {


        CartDTO customersCart = cartService.findCartByCustomer(customer.getId());

        Map<String, Item> cartItems = cart.getItems();
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            itemRepository.save(entry.getValue());
            customersCart.increment();
        }

        customersCart.getItems().putAll(cart.getItems());
        Cart cartToDB = new Cart();
        cartToDB.setId(customersCart.getId());
        Customer customer1 = customerService.getCustomerById(customer.getId());
        cartToDB.setCustomer(customer1);
        cartToDB.setItems(customersCart.getItems());
        cartToDB.setQuantity(customersCart.getQuantity());
        cartService.saveCart(cartToDB);
    }

    @Override
    public Item getItemById(String id) {
        return null;
    }

    @Override
//    @Transactional
    public boolean deleteItems(CartDTO cartDTO, CustomerDTO customerDTO) {

        Cart customersCart = cartService.findCartByCustomerBYId(customerDTO.getId());

        Map<String, Item> itemMap = new HashMap<>(customersCart.getItems());
        customersCart.getItems().clear();
        customersCart.setQuantity(0);
        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {

            deleteItemById(entry.getValue().getId());
//            itemRepository.deleteItemByItemId(entry.getValue().getItemId());
//            itemRepository.delete(entry.getValue());
//            itemRepository.deleteById(entry.getValue().getItemId());
//            itemRepository.deleteItemByItemId(entry.getValue().getItemId());
        }
        cartService.saveCart(customersCart);
        return true;
    }

    @Override
    public void deleteItemById(String id) {
        itemRepository.deleteById(id);
    }
}