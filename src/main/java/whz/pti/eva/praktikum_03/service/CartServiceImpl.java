package whz.pti.eva.praktikum_03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.CartRepository;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.security.service.user.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The class CartServiceImpl for middleware. All business logic is here
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Service
public class CartServiceImpl implements CartService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private CartRepository cartRepository;

    /**
     * Instantiates a new Cart service.
     *
     * @param cartRepository the cart repository
     */
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> listAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public boolean addCart(Cart cart) {
        log.debug("Adding cart={}", cart);
        if (cart == null) return false;

        cartRepository.save(cart);
        return true;
    }

    @Override
    public Cart findCartByCustomerBYId(String customerId) {
        log.debug("Finding cart by customerId={}", customerId);
        return cartRepository.findByCustomerId(customerId).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> Cart not found with customerId=%s", customerId)));
    }

    @Override
    public boolean deleteCart(Long id) {
        log.debug("Deleting cart by id={}", id);
        cartRepository.deleteById(id);
        return true;
    }

    @Override
    public CartDTO findCartByCustomer(String customerId) {
        log.debug("Finding cart by customerId={}", customerId);
        Cart cart = cartRepository.findByCustomerId(customerId).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> Cart not found with customerId=%s", customerId)));
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setItems(cart.getItems());
        cartDTO.setUserId(cart.getCustomer().getId());
        return cartDTO;
    }

    @Override
    public int calculateTotalAmountOfPizzasInItemsInCart(CartDTO cart) {
        log.debug("Calculating all amoung of pizzas in cart");
        Map<String, Item> cartItems = cart.getItems();
        int totalAmount = 0;
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            totalAmount += entry.getValue().getQuantity();
        }

        return totalAmount;
    }

    @Override
    public BigDecimal calculateTotalPriceOfPizzaInItemsInCart(CartDTO cart) {
        log.debug("Calculating all totalPrice of pizzas in cart");
        Map<String, Item> cartItems = cart.getItems();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            BigDecimal tempItemPrice = entry.getValue().getPizza().getPriceByEnum(entry.getValue().getPizzaSize());
            totalPrice = totalPrice.add(tempItemPrice.multiply(new BigDecimal(entry.getValue().getQuantity())));
        }

        return totalPrice;
    }

    @Override
    public Cart saveCart(Cart cart) {
        log.debug("Saving cart to database");
        return cartRepository.save(cart);
    }
}