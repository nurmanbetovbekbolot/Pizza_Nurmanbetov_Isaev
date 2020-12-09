package whz.pti.eva.praktikum_03.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.service.CustomerService;

import javax.validation.Valid;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/customer/update/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateCustomer(@PathVariable("id") String id, @Valid @ModelAttribute("customer") CustomerDTO customerDTO) {
        customerDTO.setId(id);
        customerService.updateCustomer(customerDTO);
        return "redirect:/users/managed";
    }
}
