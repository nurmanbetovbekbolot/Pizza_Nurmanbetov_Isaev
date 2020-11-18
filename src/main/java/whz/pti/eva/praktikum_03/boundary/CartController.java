package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/")
    public String toCart(Model model) {
        List<Item> itemList = itemService.listAllItems();
        model.addAttribute("itemList",itemList) ;
        return "cart";
    }
}
