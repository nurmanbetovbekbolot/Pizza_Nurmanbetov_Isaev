package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.CartRepository;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.dto.CartDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> listAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public boolean addCart(Cart cart) {
        if (cart == null) return false;

        cartRepository.save(cart);
        return true;
    }

    @Override
    public boolean deleteCart(Long id) {
        return false;
    }

    @Override
    public CartDTO findCartByCustomer(String customerId) {
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
        Map<String, Item> cartItems = cart.getItems();

        int totalAmount = 0;
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            totalAmount += entry.getValue().getQuantity();
        }

        return totalAmount;
    }

    @Override
    public BigDecimal calculateTotalPriceOfPizzaInItemsInCart(CartDTO cart) {
        Map<String, Item> cartItems = cart.getItems();

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map.Entry<String, Item> entry : cartItems.entrySet()) {
            BigDecimal tempItemPrice = entry.getValue().getPizza().getPriceByEnum(entry.getValue().getPizzaSize());
            totalPrice = totalPrice.add(tempItemPrice.multiply(new BigDecimal(entry.getValue().getQuantity())));
        }

        return totalPrice;
    }
}