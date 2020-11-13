package whz.pti.eva.praktikum_03.service;

import org.springframework.stereotype.Service;
import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.CartRepository;

import java.util.List;
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
