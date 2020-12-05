package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.*;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.User;
import whz.pti.eva.praktikum_03.security.domain.UserRepository;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.security.service.validator.UserCreateFormValidator;
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

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    public ItemController( ItemService itemService,UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }
    @PostMapping(value = "/add")
    public String addItemToCart(HttpSession session, Model model, @ModelAttribute("pizzaSize") PizzaSize pizzaSize, @RequestParam Integer menge, @RequestParam("pizzaName") String pizzaName) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);
        if (currentUser== null){
            if(session.getAttribute("cart") == null){
                session.setAttribute("cart", new Cart());
            }
            Cart cart = (Cart) session.getAttribute("cart");
            itemService.addItem(pizzaSize, menge,pizzaName, cart);
        }
        else {
            //!!!!
//            session.removeAttribute("cart");
            Customer customer =  customerRepository.findByUser(currentUser.getUser());
            if (customer != null){
                Cart cart = cartRepository.findByCustomer(customer);
                if (cart!=null) {
                    itemService.addItem(pizzaSize, menge, pizzaName, cart, customer);
                }
                else {
                    Cart cart1 = new Cart();
                    itemService.addItem(pizzaSize, menge, pizzaName, cart1, customer);
                }


            }

        }

//        if (currentUser!=null)itemService.addItem(pizzaSize, menge, pizzaName);
//        else
//            CartDto
        return "redirect:/index";
    }

    private Optional<User> getLoggedInUser(Principal principal) {
        String loginName = principal.getName();

        return userRepository.findOneByLoginName(loginName);
    }

}