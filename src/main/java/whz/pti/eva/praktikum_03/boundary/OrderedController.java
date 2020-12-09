package whz.pti.eva.praktikum_03.boundary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.Ordered;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.*;

import java.util.List;

@Controller
@RequestMapping("/ordered")
public class OrderedController {

    private PizzaService pizzaService;
    private CartService cartService;
    private ItemService itemService;
    private CustomerService customerService;
    private OrderedService orderedService;
    private OrderedItemService orderedItemService;

    @Autowired
    public OrderedController(PizzaService pizzaService, CartService cartService, ItemService itemService, CustomerService customerService, OrderedService orderedService, OrderedItemService orderedItemService) {
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
        this.orderedService = orderedService;
        this.orderedItemService = orderedItemService;
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping(value = "/")
    public String root(Model model) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

        if (currentUser != null && currentUser.getRole() != Role.ADMIN) {
            CustomerDTO currentCustomer = customerService.findByUserId(currentUser.getUser().getId());
            CartDTO customersCart = cartService.findCartByCustomer(currentCustomer.getId());
            if (customersCart.getQuantity() != 0) {
                Ordered ordered = orderedItemService.addOrderedItem(customersCart, currentCustomer);
                itemService.deleteItems(customersCart, currentCustomer);
            }
            List<Ordered> orderedList = orderedService.findAllByCustomerId(currentCustomer.getId());
            model.addAttribute("orderedList", orderedList);
            model.addAttribute("customer", currentCustomer);
        }
        return "ordered";
    }
}
