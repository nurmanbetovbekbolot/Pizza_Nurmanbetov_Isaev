package whz.pti.eva.praktikum_03.boundary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.DeliveryAddressDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.service.DeliveryAddressService;

import javax.validation.Valid;

@Controller
@RequestMapping("/deliveryaddress")
public class DeliveryAddressController {

    private DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @GetMapping(value = "/update/{id}")
    public String getEditDeliveryAddressView(@PathVariable("id") Long id,Model model) {
//        UserDTO userDTO = userService.getUserById(userId);
//        CustomerDTO customerDTO = customerService.findByUserId(userDTO.getId());
//        List<DeliveryAddress> deliveryAddresses = addressService.getDeliveryAddressesByCustomer(customerDTO.getId());
        DeliveryAddressDTO deliveryAddressDTO = deliveryAddressService.findDeliveryAddressById(id);
        model.addAttribute("deliveryaddress", deliveryAddressDTO);
        model.addAttribute("add", false);
//        model.addAttribute("userId", userId);
//        model.addAttribute("customerId", customerId);
        return "address_update";
    }
    @PostMapping(value = "/update/{id}")
    public String updateDeliveryAddress(@PathVariable("id") Long id,@ModelAttribute("deliveryaddress")DeliveryAddressDTO deliveryAddressDTO) {

        deliveryAddressDTO.setId(id);
        deliveryAddressService.updateAddress(deliveryAddressDTO);
        return "redirect:/users/managed/";
//        return "redirect:/users/"+customerId;
    }
}
