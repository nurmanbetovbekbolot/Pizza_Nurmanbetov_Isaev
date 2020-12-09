package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.dto.CartDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<Cart> listAllCarts();

    boolean addCart(Cart cart);

    boolean deleteCart(Long id);

    int calculateTotalAmountOfPizzasInItemsInCart(CartDTO cart);

    BigDecimal calculateTotalPriceOfPizzaInItemsInCart(CartDTO cart);

    CartDTO findCartByCustomer(String customerId);

    Cart findCartByCustomerBYId(String customerId);

    Cart saveCart(Cart cart);

}