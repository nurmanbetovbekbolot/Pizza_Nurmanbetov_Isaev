package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private CartService cartService;


    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

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
        cartDTO.getItems().put(UUID.randomUUID().toString(), item);
        cartDTO.increment();
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO,cart);
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        cart.setCustomer(customer);
        cartRepository.save(cart);
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
        Map<String, Item> cartItems = cart.getItems();
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            itemRepository.save(entry.getValue());
        }

        CartDTO customersCart = cartService.findCartByCustomer(customer.getId());
        customersCart.getItems().putAll(cart.getItems());
        Cart cartToDB = new Cart();
        BeanUtils.copyProperties(customersCart,cartToDB);
        cartRepository.save(cartToDB);
    }

    @Override
    public Item getItemById(String id) {
        return null;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }
}