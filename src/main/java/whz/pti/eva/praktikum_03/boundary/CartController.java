package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    private ItemService itemService;

    private CustomerService customerService;

    @Autowired
    public CartController(CartService cartService, ItemService itemService, CustomerService customerService) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    @GetMapping(value = "/")
    public String toCart(HttpSession session, Model model) {

        CurrentUser currentUser = CurrentUserUtil.getUser(model);
        if (currentUser== null){
            if(session.getAttribute("cart") != null){
                Cart cart = (Cart) session.getAttribute("cart");
                Map<String, Item> cartItems = cart.getItems();
                model.addAttribute("itemList",cartItems) ;
            }
        }
        else {
            Customer customer =  customerService.findByUser(currentUser.getUser());
            if (customer != null){
                Cart cart = cartService.findCartByCustomer(customer);
                if (cart!=null) {
                    Map<String, Item> cartItems = cart.getItems();
                    model.addAttribute("itemList",cartItems) ;
                }
            }
        }

        return "cart";
    }
}