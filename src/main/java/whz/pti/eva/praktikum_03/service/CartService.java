package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> listAllCarts();

    boolean addCart(Cart cart);

    boolean deleteCart(Long id);

    int calculateTotalAmountOfPizzasInItemsInCart(CartDTO cart);

    BigDecimal calculateTotalPriceOfPizzaInItemsInCart(CartDTO cart);

    CartDTO findCartByCustomer(String customerId);

}