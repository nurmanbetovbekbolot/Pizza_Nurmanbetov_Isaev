package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.service.ItemService;
import whz.pti.eva.praktikum_03.service.PizzaService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/all")
    public String listAllPizza(Model model) {
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);
//        int totalAmount = itemService.calculateTotalAmountOfPizzaInItems();
//        BigDecimal totalPrice = itemService.calculateTotalPriceOfPizzaInItems();
//
//        model.addAttribute("totalAmount", totalAmount);
//        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }
}