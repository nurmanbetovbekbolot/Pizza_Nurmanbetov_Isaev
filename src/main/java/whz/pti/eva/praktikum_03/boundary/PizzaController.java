package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.service.PizzaService;

import java.util.List;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping("/all")
    public String listAllPizza(Model model) {
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);
        return "index";
    }
}
