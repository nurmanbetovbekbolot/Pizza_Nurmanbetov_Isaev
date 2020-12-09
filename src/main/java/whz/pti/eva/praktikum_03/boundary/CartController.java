package whz.pti.eva.praktikum_03.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.Item;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.service.validator.UserCreateFormValidator;
import whz.pti.eva.praktikum_03.service.CartService;
import whz.pti.eva.praktikum_03.service.CustomerService;
import whz.pti.eva.praktikum_03.service.ItemService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The class Cart controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    private ItemService itemService;

    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    /**
     * Instantiates a new Cart controller.
     *
     * @param cartService     the cart service
     * @param itemService     the item service
     * @param customerService the customer service
     */
    @Autowired
    public CartController(CartService cartService, ItemService itemService, CustomerService customerService) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
    }


    /**
     * This method show the Cart (Warenkorb).
     *
     * @param session the session
     * @param model   the model
     * @return the cart
     */
    @GetMapping(value = "/")
    public String toCart(HttpSession session, Model model) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);
        if (currentUser == null) {
            if (session.getAttribute("cart") != null) {
                CartDTO cart = (CartDTO) session.getAttribute("cart");
                Map<String, Item> cartItems = cart.getItems();
                log.info("Show cart items for anonym acc");
                model.addAttribute("itemList", cartItems);
            }
        } else if (currentUser.getRole() != Role.ADMIN) {
            CustomerDTO customer = customerService.findByUserId(currentUser.getUser().getId());
            if (customer != null) {
                CartDTO cart = cartService.findCartByCustomer(customer.getId());
                if (cart != null) {
                    Map<String, Item> cartItems = cart.getItems();
                    log.info("Show cart items for loggedIn acc");
                    model.addAttribute("itemList", cartItems);
//                    session.setAttribute("items", cartItems);
                }
            }
        }

        return "cart";
    }

    /**
     * Remove Item from Cart.
     *
     * @param session the session
     * @param model   the model
     * @param itemKey the item key
     * @return redirect to cart
     */
    @RequestMapping(value = "/remove", method = {RequestMethod.POST, RequestMethod.GET})
    public String removeItemFromCart(HttpSession session, Model model, @RequestParam("itemKey") String itemKey) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

        if (currentUser == null) {
            CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
            if (cartDTO != null) {
                cartDTO.getItems().remove(itemKey);
                log.info("Remove item from session");
                session.setAttribute("cart", cartDTO);
            }
        } else if (currentUser.getRole() != Role.ADMIN) {
            CustomerDTO customer = customerService.findByUserId(currentUser.getUser().getId());
            if (customer != null) {
                CartDTO cart = cartService.findCartByCustomer(customer.getId());
                if (cart != null) {
                    log.info("Remove item from customers cart");
                    itemService.deleteItemInCart(cart, customer, itemKey);
                }
            }
        }
        return "redirect:/cart/";
    }

    /**
     * Decrease quantity of Item.
     *
     * @param session the session
     * @param model   the model
     * @param itemKey the item key
     * @return redirect to cart
     */
    @RequestMapping(value = "/decrease", method = {RequestMethod.POST, RequestMethod.GET})
    public String decrease(HttpSession session, Model model, @RequestParam("itemKey") String itemKey) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

        if (currentUser == null) {
            CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
            if (cartDTO != null && cartDTO.getItems().get(itemKey).getQuantity() > 1) {
                cartDTO.getItems().get(itemKey).setQuantity(cartDTO.getItems().get(itemKey).getQuantity() - 1);
                log.info("Decrease item quantity from session");
                session.setAttribute("cart", cartDTO);
            }
        } else if (currentUser.getRole() != Role.ADMIN) {
            CustomerDTO customer = customerService.findByUserId(currentUser.getUser().getId());
            if (customer != null) {
                CartDTO cart = cartService.findCartByCustomer(customer.getId());
                if (cart != null && cart.getItems().get(itemKey).getQuantity() > 1) {
                    log.info("Decrease item quantity from customers cart");
                    itemService.decreaseItemQuantity(cart, customer, itemKey);
                }
            }
        }
        return "redirect:/cart/";
    }

    /**
     * Increase string.
     *
     * @param session the session
     * @param model   the model
     * @param itemKey the item key
     * @param item    the item
     * @return redirect to cart
     */
    @RequestMapping(value = "/increase", method = {RequestMethod.POST, RequestMethod.GET})
    public String increase(HttpSession session, Model model, @RequestParam("itemKey") String itemKey, @ModelAttribute("item") Item item) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

        if (currentUser == null) {
            CartDTO cartDTO = (CartDTO) session.getAttribute("cart");
            if (cartDTO != null) {
                cartDTO.getItems().get(itemKey).setQuantity(cartDTO.getItems().get(itemKey).getQuantity() + 1);
                log.info("Increase item quantity from session");
                session.setAttribute("cart", cartDTO);
            }
        } else if (currentUser.getRole() != Role.ADMIN) {
            CustomerDTO customer = customerService.findByUserId(currentUser.getUser().getId());
            if (customer != null) {
                CartDTO cart = cartService.findCartByCustomer(customer.getId());
                if (cart != null) {
                    log.info("Increase item quantity from customers cart");
                    itemService.increaseItemQuantity(cart, customer, itemKey);
                }
            }
        }
        return "redirect:/cart/";
    }


}