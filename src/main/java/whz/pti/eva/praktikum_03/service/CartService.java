package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> listAllCarts();

    boolean addCart(Cart cart);

    boolean deleteCart(Long id);

    int calculateTotalAmountOfPizzasInItemsInCart(Cart cart);

    BigDecimal calculateTotalPriceOfPizzaInItemsInCart(Cart cart);
}