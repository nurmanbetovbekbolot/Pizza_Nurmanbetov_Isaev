package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderedService orderedService;

    @Autowired
    private OrderedItemService orderedItemService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        return "login";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "error/403";
    }

    @RequestMapping("/index")
    public String root(Model model) {
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);

        int totalAmount = itemService.calculateTotalAmountOfPizzaInItems();
        BigDecimal totalPrice = itemService.calculateTotalPriceOfPizzaInItems();

        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }
}
