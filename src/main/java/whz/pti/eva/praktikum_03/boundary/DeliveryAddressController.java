package whz.pti.eva.praktikum_03.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;
import whz.pti.eva.praktikum_03.service.DeliveryAddressService;

/**
 * The class Delivery address controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
@RequestMapping("/deliveryaddress")
public class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    private static final Logger log = LoggerFactory.getLogger(DeliveryAddressController.class);

    /**
     * Instantiates a new Delivery address controller.
     *
     * @param deliveryAddressService the delivery address service
     */
    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }


    /**
     * Gets edit delivery address.
     *
     * @param id    the id
     * @param model the model
     * @return address_update
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/update/{id}")
    public String getEditDeliveryAddressView(@PathVariable("id") Long id, Model model) {
        log.info("Get delivery address");
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressService.findDeliveryAddressById(id);
        model.addAttribute("deliveryaddress", deliveryAddressDTO);
        model.addAttribute("add", false);
        return "address_update";
    }

    /**
     * Update delivery address string.
     *
     * @param id                 the id
     * @param deliveryAddressDTO the delivery address dto
     * @return redirect:/users/managed/
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update/{id}")
    public String updateDeliveryAddress(@PathVariable("id") Long id, @ModelAttribute("deliveryaddress") DeliveryAddressDTO deliveryAddressDTO) {
        log.info("Update delivery address");
        deliveryAddressDTO.setId(id);
        deliveryAddressService.updateAddress(deliveryAddressDTO);
        return "redirect:/users/managed/";
//        return "redirect:/users/"+customerId;
    }
}
