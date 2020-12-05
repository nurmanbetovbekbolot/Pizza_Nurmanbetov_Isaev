package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

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
    public void addItem(PizzaSize pizzaSize, Integer quantity, String pizzaName, Cart cart, Customer customer) {
        Item item = new Item();
        Pizza pizzaInDb = pizzaService.findPizzaByName(pizzaName);
        item.setQuantity(quantity);
        item.setPizza(pizzaInDb);
        item.setPizzaSize(pizzaSize);
        itemRepository.save(item);
        cart.getItems().put(UUID.randomUUID().toString(), item);
        cart.increment();
        cart.setCustomer(customer);
        cartRepository.save(cart);
    }

    //For NotLogedIn
    @Override
    public void addItem(PizzaSize pizzaSize, Integer quantity, String pizzaName, Cart cart) {
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
    public void updateCustomersCart(Cart cart, Customer customer) {
        Map<String, Item> cartItems = cart.getItems();
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            itemRepository.save(entry.getValue());
        }

        Cart customersCart = cartService.findCartByCustomer(customer);
        customersCart.getItems().putAll(cart.getItems());
        cartRepository.save(customersCart);
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