package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.service.PizzaService;

import java.util.List;

/**
 * The class Pizza controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private PizzaService pizzaService;

    /**
     * Instantiates a new Pizza controller.
     *
     * @param pizzaService the pizza service
     */
    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /**
     * List all pizza string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/all")
    public String listAllPizza(Model model) {
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);
        return "index";
    }
}