package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.security.service.validator.UserCreateFormValidator;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;
import whz.pti.eva.praktikum_03.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;
    private UserService userService;
    private PizzaService pizzaService;
    private CartService cartService;
    private CustomerService customerService;

    @Autowired
    public ItemController(ItemService itemService, UserService userService, PizzaService pizzaService, CartService cartService, CustomerService customerService) {
        this.itemService = itemService;
        this.userService = userService;
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/add")
    public String addItemToCart(HttpSession session, Model model, @ModelAttribute("pizzaSize") PizzaSize pizzaSize, @RequestParam Integer menge, @RequestParam("pizzaName") String pizzaName) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);
        if (currentUser== null){
            if(session.getAttribute("cart") == null){
                session.setAttribute("cart", new CartDTO());
            }
            CartDTO cart = (CartDTO) session.getAttribute("cart");
            itemService.addItem(pizzaSize, menge,pizzaName, cart);
        }
        else {
            //!!!!
//            session.removeAttribute("cart");
            CustomerDTO customer =  customerService.findByUserId(currentUser.getUser().getId());
            if (customer != null){
                CartDTO cart = cartService.findCartByCustomer(customer.getId());
                if (cart!=null) {
                    itemService.addItem(pizzaSize, menge, pizzaName, cart, customer);
                }
                else {
                    CartDTO cart1 = new CartDTO();
                    itemService.addItem(pizzaSize, menge, pizzaName, cart1, customer);
                }


            }

        }

        return "redirect:/index";
    }
//
//    private Optional<User> getLoggedInUser(Principal principal) {
//        String loginName = principal.getName();
//
//        return userRepository.findOneByLoginName(loginName);
//    }

}