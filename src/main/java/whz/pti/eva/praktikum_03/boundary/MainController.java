package whz.pti.eva.praktikum_03.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.Pizza;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;
import whz.pti.eva.praktikum_03.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The class Main (Pizza Auswahl) controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
public class MainController {
    private PizzaService pizzaService;
    private CartService cartService;
    private ItemService itemService;
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    /**
     * Instantiates a new Main controller.
     *
     * @param pizzaService    the pizza service
     * @param cartService     the cart service
     * @param itemService     the item service
     * @param customerService the customer service
     */
    @Autowired
    public MainController(PizzaService pizzaService, CartService cartService, ItemService itemService, CustomerService customerService) {
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
    }

    /**
     * Gets login page.
     *
     * @param error the error
     * @param model the model
     * @return login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error, Model model) {
        return "login";
    }

    /**
     * Access denied.
     *
     * @return error/403
     */
    @RequestMapping("/403")
    public String accessDenied() {
        return "error/403";
    }

    /**
     * Root string.
     *
     * @param session the session
     * @param model   the model
     * @return index
     */
    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String root(HttpSession session, Model model) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);
        List<Pizza> pizzaList = pizzaService.listAllPizza();
        model.addAttribute("pizzaList", pizzaList);
        int totalAmount = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;

        if (currentUser != null && currentUser.getRole() != Role.ADMIN) {
            CustomerDTO currentCustomer = customerService.findByUserId(currentUser.getUser().getId());
            CartDTO customersCart = cartService.findCartByCustomer(currentCustomer.getId());
            if (customersCart != null) {
                CartDTO cart = (CartDTO) session.getAttribute("cart");

                if (cart != null) {
                    log.info("Merge cart from session to customers cart");
                    itemService.updateCustomersCart(cart, currentCustomer);
                    session.removeAttribute("cart");
                }
                totalAmount = cartService.calculateTotalAmountOfPizzasInItemsInCart(customersCart);
                totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(customersCart);
            }
        } else if (currentUser == null) {
            CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
            if (cartDTO != null) {
                totalAmount = cartService.calculateTotalAmountOfPizzasInItemsInCart(cartDTO);
                totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(cartDTO);
            }
        }
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalPrice", totalPrice);
        return "index";
    }
}