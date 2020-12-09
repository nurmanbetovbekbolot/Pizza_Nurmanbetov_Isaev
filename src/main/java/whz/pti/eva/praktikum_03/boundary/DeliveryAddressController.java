package whz.pti.eva.praktikum_03.boundary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;
import whz.pti.eva.praktikum_03.service.DeliveryAddressService;

@Controller
@RequestMapping("/deliveryaddress")
public class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/update/{id}")
    public String getEditDeliveryAddressView(@PathVariable("id") Long id, Model model) {
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressService.findDeliveryAddressById(id);
        model.addAttribute("deliveryaddress", deliveryAddressDTO);
        model.addAttribute("add", false);
        return "address_update";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update/{id}")
    public String updateDeliveryAddress(@PathVariable("id") Long id, @ModelAttribute("deliveryaddress") DeliveryAddressDTO deliveryAddressDTO) {

        deliveryAddressDTO.setId(id);
        deliveryAddressService.updateAddress(deliveryAddressDTO);
        return "redirect:/users/managed/";
//        return "redirect:/users/"+customerId;
    }
}
