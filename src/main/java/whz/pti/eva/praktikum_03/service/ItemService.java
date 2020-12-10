package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;

import java.util.List;

/**
 * The interface Item service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface ItemService {

    /**
     * List all items list.
     *
     * @return the list
     */
    List<Item> listAllItems();

    /**
     * Add item boolean.
     *
     * @param item the item
     * @return the boolean
     */
    boolean addItem(Item item);

    /**
     * Add item.
     *
     * @param pizzaSize   the pizza size
     * @param amount      the amount
     * @param pizza       the pizza
     * @param cart        the cart
     * @param customerDTO the customer dto
     */
    void addItem(PizzaSize pizzaSize, Integer amount, String pizza, CartDTO cart, CustomerDTO customerDTO);


    /**
     * Add item.
     *
     * @param pizzaSize the pizza size
     * @param amount    the amount
     * @param pizza     the pizza
     * @param cart      the cart
     */
    void addItem(PizzaSize pizzaSize, Integer amount, String pizza, CartDTO cart);

    /**
     * Update customers cart.
     *
     * @param cart     the cart
     * @param customer the customer
     */
    void updateCustomersCart(CartDTO cart, CustomerDTO customer);

    /**
     * Gets item by id.
     *
     * @param id the id
     * @return the item by id
     */
    Item getItemById(String id);

    /**
     * Save item item.
     *
     * @param item the item
     * @return the item
     */
    Item saveItem(Item item);

    /**
     * Delete item by id.
     *
     * @param id the id
     */
    void deleteItemById(String id);

    /**
     * Delete item in cart.
     *
     * @param cartDTO     the cart dto
     * @param customerDTO the customer dto
     * @param itemKey     the item key
     */
    void deleteItemInCart(CartDTO cartDTO, CustomerDTO customerDTO, String itemKey);

    /**
     * Delete items boolean.
     *
     * @param cartDTO     the cart dto
     * @param customerDTO the customer dto
     * @return the boolean
     */
    boolean deleteItems(CartDTO cartDTO, CustomerDTO customerDTO);

    /**
     * Decrease item quantity.
     *
     * @param cartDTO     the cart dto
     * @param customerDTO the customer dto
     * @param itemKey     the item key
     */
    void decreaseItemQuantity(CartDTO cartDTO, CustomerDTO customerDTO, String itemKey);

    /**
     * Increase item quantity.
     *
     * @param cartDTO     the cart dto
     * @param customerDTO the customer dto
     * @param itemKey     the item key
     */
    void increaseItemQuantity(CartDTO cartDTO, CustomerDTO customerDTO, String itemKey);

}