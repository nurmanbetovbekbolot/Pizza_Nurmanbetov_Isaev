package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.service.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private PizzaService pizzaService;
    private CartService cartService;
    private ItemService itemService;
    private CustomerService customerService;

    @Autowired
    public MainController(PizzaService pizzaService, CartService cartService, ItemService itemService, CustomerService customerService) {
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "error/403";
    }

    @RequestMapping(value = {"/","/index"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String root(HttpSession session, Model model) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);


        int totalAmount = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;

        if (currentUser != null){
            Customer currentCustomer =  customerService.findByUser(currentUser.getUser());
            Cart customersCart = cartService.findCartByCustomer(currentCustomer);
            if (customersCart != null){
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart!=null) itemService.updateCustomersCart(cart, currentCustomer);

                totalAmount = cartService.calculateTotalAmountOfPizzasInItemsInCart(customersCart);
                totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(customersCart);
            }
        }
        else {
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart!=null){
                totalAmount = cartService.calculateTotalAmountOfPizzasInItemsInCart(cart);
                totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(cart);
            }
        }

        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }
}