package whz.pti.eva.praktikum_03.boundary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

//    @GetMapping(value = "/")
//    public String toOrdered(Model model) {
//        CurrentUser currentUser = CurrentUserUtil.getUser(model);
//        if (currentUser== null){
//            if(session.getAttribute("cart") != null){
//                CartDTO cart = (CartDTO) session.getAttribute("cart");
//                Map<String, Item> cartItems = cart.getItems();
//                model.addAttribute("itemList",cartItems) ;
//            }
//        }
//        else {
//            CustomerDTO customer =  customerService.findByUserId(currentUser.getUser().getId());
//            if (customer != null){
//                CartDTO cart = cartService.findCartByCustomer(customer.getId());
//                if (cart!=null) {
//                    Map<String, Item> cartItems = cart.getItems();
//                    model.addAttribute("itemList",cartItems) ;
////                    session.setAttribute("items", cartItems);
//                }
//            }
//        }

//        return "ordered";
//    }

    @GetMapping(value = "/")
    public String root(Model model) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

//        int totalAmount = 0;
//        BigDecimal totalPrice = BigDecimal.ZERO;

        if (currentUser != null && currentUser.getRole() != Role.ADMIN) {
            CustomerDTO currentCustomer = customerService.findByUserId(currentUser.getUser().getId());
            CartDTO customersCart = cartService.findCartByCustomer(currentCustomer.getId());
            if (customersCart != null) {
                orderedItemService.addOrderedItem(customersCart, currentCustomer);
                itemService.deleteItems(customersCart, currentCustomer);
//                totalAmount = cartService.calculateTotalAmountOfPizzasInItemsInCart(customersCart);
//                totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(customersCart);
            }
        } else if (currentUser == null){
//            CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
//            if (cartDTO != null) {
//                totalAmount = cartService.calculateTotalAmountOfPizzasInItemsInCart(cartDTO);
//                totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(cartDTO);
//            }
        }

//        model.addAttribute("totalAmount", totalAmount);
//        model.addAttribute("totalPrice", totalPrice);
        return "ordered";
    }
}
