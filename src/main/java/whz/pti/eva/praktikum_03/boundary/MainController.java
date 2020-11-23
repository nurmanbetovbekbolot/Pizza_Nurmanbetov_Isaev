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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private PizzaService pizzaService;
    private CartService cartService;
    private ItemService itemService;

    private OrderedService orderedService;

    private OrderedItemService orderedItemService;

    private UserService userService;


    @Autowired
    public MainController(PizzaService pizzaService, CartService cartService, ItemService itemService, OrderedService orderedService, OrderedItemService orderedItemService,UserService userService) {
  
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.orderedService = orderedService;
        this.orderedItemService = orderedItemService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "error/403";
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String root(Model model) {
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);
        model.addAttribute("loggedInUser", currentUser);
        int totalAmount = itemService.calculateTotalAmountOfPizzaInItems();
        BigDecimal totalPrice = itemService.calculateTotalPriceOfPizzaInItems();
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }
}
