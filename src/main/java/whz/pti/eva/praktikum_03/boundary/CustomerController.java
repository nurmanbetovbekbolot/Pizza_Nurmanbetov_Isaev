package whz.pti.eva.praktikum_03.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * The class Customer controller.
 *
 * @author Isaev A. Nurmanbetov B.
 */
@Controller
public class CustomerController {

    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService the customer service
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Update customer.
     *
     * @param id          the id
     * @param customerDTO the customer dto
     * @return redirect to users/managed
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/customer/update/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateCustomer(@PathVariable("id") String id, @Valid @ModelAttribute("customer") CustomerDTO customerDTO) {
        customerDTO.setId(id);
        log.info("Update customer");
        customerService.updateCustomer(customerDTO);
        return "redirect:/users/managed";
    }
}
