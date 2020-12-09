package whz.pti.eva.praktikum_03.boundary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.praktikum_03.domain.OrderedItem;
import whz.pti.eva.praktikum_03.dto.Source;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;
import whz.pti.eva.praktikum_03.service.PizzaService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/ordereditem")
public class OrderedItemController {
    private PizzaService pizzaService;
    private CartService cartService;
    private ItemService itemService;
    private CustomerService customerService;

    @Autowired
    public OrderedItemController(PizzaService pizzaService, CartService cartService, ItemService itemService, CustomerService customerService) {
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/add")
    public String addItemToOrderedItem(HttpSession session, Model model, @ModelAttribute("source") Source source) {



        return "cart";
    }
}
