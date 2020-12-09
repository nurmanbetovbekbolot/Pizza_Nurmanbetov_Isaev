package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.dto.CartDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Cart service.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public interface CartService {

    /**
     * List all carts list.
     *
     * @return the list
     */
    List<Cart> listAllCarts();

    /**
     * Add cart boolean.
     *
     * @param cart the cart
     * @return the boolean
     */
    boolean addCart(Cart cart);

    /**
     * Delete cart boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteCart(Long id);

    /**
     * Calculate total amount of pizzas in items in cart int.
     *
     * @param cart the cart
     * @return the int
     */
    int calculateTotalAmountOfPizzasInItemsInCart(CartDTO cart);

    /**
     * Calculate total price of pizza in items in cart big decimal.
     *
     * @param cart the cart
     * @return the big decimal
     */
    BigDecimal calculateTotalPriceOfPizzaInItemsInCart(CartDTO cart);

    /**
     * Find cart by customer cart dto.
     *
     * @param customerId the customer id
     * @return the cart dto
     */
    CartDTO findCartByCustomer(String customerId);

    /**
     * Find cart by customer by id cart.
     *
     * @param customerId the customer id
     * @return the cart
     */
    Cart findCartByCustomerBYId(String customerId);

    /**
     * Save cart cart.
     *
     * @param cart the cart
     * @return the cart
     */
    Cart saveCart(Cart cart);

}