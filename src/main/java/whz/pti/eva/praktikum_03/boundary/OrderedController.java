package whz.pti.eva.praktikum_03.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.Ordered;
import whz.pti.eva.praktikum_03.dto.CartDTO;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.PayActionResponseDTO;
import whz.pti.eva.praktikum_03.enums.Role;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.service.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * The class Ordered controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
@RequestMapping("/ordered")
public class OrderedController {

    private PizzaService pizzaService;
    private CartService cartService;
    private ItemService itemService;
    private CustomerService customerService;
    private OrderedService orderedService;
    private OrderedItemService orderedItemService;
    private PaymentService paymentService;

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    /**
     * Instantiates a new Ordered controller.
     *
     * @param pizzaService       the pizza service
     * @param cartService        the cart service
     * @param itemService        the item service
     * @param customerService    the customer service
     * @param orderedService     the ordered service
     * @param orderedItemService the ordered item service
     */
    @Autowired
    public OrderedController(PizzaService pizzaService, CartService cartService, ItemService itemService, CustomerService customerService, OrderedService orderedService, OrderedItemService orderedItemService, PaymentService paymentService) {
        this.pizzaService = pizzaService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.customerService = customerService;
        this.orderedService = orderedService;
        this.orderedItemService = orderedItemService;
        this.paymentService = paymentService;
    }

    /**
     * Ordered index.
     *
     * @param model the model
     * @return ordered
     */
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String root(Model model) {
        CurrentUser currentUser = CurrentUserUtil.getUser(model);

        if (currentUser != null && currentUser.getRole() != Role.ADMIN) {
            CustomerDTO currentCustomer = customerService.findByUserId(currentUser.getUser().getId());
            CartDTO customersCart = cartService.findCartByCustomer(currentCustomer.getId());
            if (customersCart.getQuantity() != 0) {
                PayActionResponseDTO payActionResponseDTO = paymentService.doPayAction(currentCustomer.getLoginName(), "pizzaService", "get");
                BigDecimal clientBalance = new BigDecimal(payActionResponseDTO.getDescription());
                BigDecimal totalPrice = cartService.calculateTotalPriceOfPizzaInItemsInCart(customersCart);
                if (clientBalance.compareTo(totalPrice) >= 0 ) {
                    log.info("Balance is "+ clientBalance);
                    log.info("Ordered is "+ totalPrice);
                    paymentService.doPayAction(currentCustomer.getLoginName(), "pizzaService", "transfer pizzaService "+totalPrice);
                    log.info("Creating ordered and ordered items. Delete items from customers cart");
                    Ordered ordered = orderedItemService.addOrderedItem(customersCart, currentCustomer);
                    itemService.deleteItems(customersCart, currentCustomer);
                    model.addAttribute("balance",1);

                }
                else {
                    log.info("Balance is less");
                    model.addAttribute("balance",0);
                }




            }
            List<Ordered> orderedList = orderedService.findAllByCustomerId(currentCustomer.getId());
            model.addAttribute("orderedList", orderedList);
            model.addAttribute("customer", currentCustomer);

        }
        return "ordered";
    }
}
