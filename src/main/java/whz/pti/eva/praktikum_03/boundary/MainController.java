package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.service.*;

import java.util.List;

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

    @RequestMapping("/all")
    public String listAllPizza(Model model) {
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        List<Cart> cartList = cartService.listAllCarts();
        List<Item> itemList = itemService.listAllItems();
        List<Ordered> orderedList = orderedService.listAllOrdered();
        List<OrderedItem> orderedItemList = orderedItemService.listAllOrderedItem();
        model.addAttribute("pizzaList", pizzaList);
        model.addAttribute("cartList", cartList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("orderedList", orderedList);
        model.addAttribute("orderedItemList", orderedItemList);
        return "pizza";
    }
}
