package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.security.service.validator.UserCreateFormValidator;
import whz.pti.eva.praktikum_03.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;
    private UserService userService;


    @Autowired
    public ItemController( ItemService itemService,UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }
    @PostMapping(value = "/add")
    public String addItemToCart(@ModelAttribute("pizzaSize") PizzaSize pizzaSize, @RequestParam Integer menge, @RequestParam("pizzaName") String pizzaName) {
        itemService.addItem(pizzaSize, menge, pizzaName);
        return "redirect:/index";
    }

}