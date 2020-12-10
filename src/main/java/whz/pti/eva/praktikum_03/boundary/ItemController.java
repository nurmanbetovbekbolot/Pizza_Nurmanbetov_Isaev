package whz.pti.eva.praktikum_03.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.PizzaSize;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;

import javax.servlet.http.HttpSession;

/**
 * The class Item controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;
    private CartService cartService;
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    /**
     * Instantiates a new Item controller.
     *
     * @param itemService     the item service
     * @param cartService     the cart service
     * @param customerService the customer service
     */
    @Autowired
    public ItemController(ItemService itemService, CartService cartService, CustomerService customerService) {
        this.itemService = itemService;
        this.cartService = cartService;
        this.customerService = customerService;
    }

    /**
     * Add item to cart string.
     *
     * @param session   the session
     * @param model     the model
     * @param pizzaSize the pizza size
     * @param menge     the menge
     * @param pizzaName the pizza name
     * @return redirect:/index
     */
    @PostMapping(value = "/add")
    public String addItemToCart(HttpSession session, Model model, @ModelAttribute("pizzaSize") PizzaSize pizzaSize, @RequestParam Integer menge, @RequestParam("pizzaName") String pizzaName) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);
        if (currentUser == null) {
            if (session.getAttribute("cart") == null) {
                log.info("Add item to session");
                session.setAttribute("cart", new CartDTO());
            }
            CartDTO cart = (CartDTO) session.getAttribute("cart");
            itemService.addItem(pizzaSize, menge, pizzaName, cart);
        } else if (currentUser.getRole() != Role.ADMIN) {
            CustomerDTO customer = customerService.findByUserId(currentUser.getUser().getId());
            if (customer != null) {
                CartDTO cart = cartService.findCartByCustomer(customer.getId());
                if (cart != null) {
                    log.info("Add item to customers cart");
                    itemService.addItem(pizzaSize, menge, pizzaName, cart, customer);
                } else {
                    CartDTO cart1 = new CartDTO();
                    itemService.addItem(pizzaSize, menge, pizzaName, cart1, customer);
                }
            }
        }
        return "redirect:/index";
    }
}