package whz.pti.eva.praktikum_03.service;

import org.springframework.beans.factory.annotation.Autowired;
import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.CartRepository;
import whz.pti.eva.praktikum_03.domain.ItemRepository;

import java.util.List;

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
        if(cart==null) return false;

        cartRepository.save(cart);
        return true;
    }

    @Override
    public Cart geCartById(Long id) {
        return null;
    }

    @Override
    public boolean deleteCart(Long id) {
        return false;
    }
}
