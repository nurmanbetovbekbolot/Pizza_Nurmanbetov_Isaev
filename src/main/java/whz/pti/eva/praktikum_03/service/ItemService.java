package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    List<Item> listAllItems();

    boolean addItem(Item item);

    void addItem(PizzaSize pizzaSize, Integer amount, String pizza,  CartDTO cart, CustomerDTO customerDTO);


    void addItem(PizzaSize pizzaSize, Integer amount, String pizza, CartDTO cart);

    void updateCustomersCart(CartDTO cart, CustomerDTO customer);

    Item getItemById(String id);

    boolean deleteItem(String id);
}