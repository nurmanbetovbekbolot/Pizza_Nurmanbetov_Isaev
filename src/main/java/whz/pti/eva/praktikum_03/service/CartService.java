package whz.pti.eva.praktikum_03.service;

import whz.pti.eva.praktikum_03.domain.Cart;

import java.util.List;

public interface CartService {

    List<Cart> listAllCarts();

    boolean addCart(Cart cart);

    Cart geCartById(Long id);

    boolean deleteCart(Long id);
}
