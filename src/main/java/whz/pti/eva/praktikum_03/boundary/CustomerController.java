package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;

//    @RequestMapping(value = "{userId}/customer/{id}", method = {RequestMethod.POST, RequestMethod.GET})
//    public String getEditCustomerView(@PathVariable("userId") String userId,@PathVariable("id") String id, Model model) {
//        UserDTO userDTO = userService.getUserById(userId);
//        Customer customerDTO =customerService.findByUserId(userDTO.getId());
//        model.addAttribute("customer", customerDTO);
//        model.addAttribute("add",false);
//        model.addAttribute("user", userDTO);
//        return "user_update";
//    }

    @RequestMapping(value = "/customer/update/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateCustomer(@PathVariable("id") String id ,@Valid @ModelAttribute("customer") CustomerDTO customerDTO) {
        customerDTO.setId(id);
        customerService.updateCustomer(customerDTO);
        return "redirect:/users/managed";
    }
}
