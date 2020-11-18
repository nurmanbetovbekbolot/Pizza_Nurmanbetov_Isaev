package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/add")
    public String addItemToCart( @ModelAttribute("pizzaSize") PizzaSize pizzaSize, @RequestParam Integer menge, @RequestParam("pizzaName") String pizzaName) {
        itemService.addItem(pizzaSize, menge, pizzaName);
        return "redirect:/";
    }
    @GetMapping(value = "/add")
    public String getInfo(Model model,@ModelAttribute("pizzaSize") PizzaSize pizzaSize, @RequestParam Integer menge, @RequestParam("pizzaName") String pizzaName ) {
      model.addAttribute("zwer",menge);
//        itemService.addItem(pizzaSize,menge, pizzaName);
        return "redirect:/";
    }
}
