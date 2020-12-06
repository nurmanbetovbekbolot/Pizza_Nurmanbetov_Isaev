package whz.pti.eva.praktikum_03.security.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.praktikum_03.common.CurrentUserUtil;
import whz.pti.eva.praktikum_03.domain.Cart;
import whz.pti.eva.praktikum_03.domain.CartRepository;
import whz.pti.eva.praktikum_03.domain.Customer;
import whz.pti.eva.praktikum_03.domain.CustomerRepository;
import whz.pti.eva.praktikum_03.dto.CustomerDTO;
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.security.service.validator.UserCreateFormValidator;
import whz.pti.eva.praktikum_03.service.CustomerService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private CustomerService customerService;
    private UserService userService;
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(CustomerService customerService, UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.customerService = customerService;
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("myform")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping("/users")
    public String getUsersPage(Model model) {
        log.info("Getting users page");
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    //    @PreAuthorize("#id==principal.id or hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/users/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserPage(@PathVariable String id, Model model) {
        log.debug("Getting user page for user= " + id);

        UserDTO userDTO = userService.getUserById(id);
        CustomerDTO customerDTO =customerService.findByUserId(id);
                model.addAttribute("user", userDTO);
                model.addAttribute("customer", customerDTO);
        return "user";
    }


    @RequestMapping(value = "/users/managed", method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserManagedPage(Model model) {
        log.debug("Getting user create form");
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        model.addAttribute("loggedInUser", currentUser);
        model.addAttribute("myform", new UserCreateForm());
        model.addAttribute("users", userService.getAllUsers());
        return "user_create";
    }

    @RequestMapping(value = "users/{userId}/customer/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String getEditCustomerView(@PathVariable("userId") String userId,@PathVariable("id") String id, Model model) {
        UserDTO userDTO = userService.getUserById(userId);
        CustomerDTO customerDTO =customerService.findByUserId(userDTO.getId());
        model.addAttribute("customer", customerDTO);
        model.addAttribute("add",false);
        model.addAttribute("user", userDTO);
        return "user_update";
    }



        @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String handleUserCreateForm(HttpSession session,@Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        log.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        model.addAttribute("loggedInUser",currentUser);
        model.addAttribute("users", userService.getAllUsers());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }
            userService.create(form,(Cart) session.getAttribute("cart"));
        return "redirect:/users/managed";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleCustomerCreate(HttpSession session, @Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        log.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }

//        CurrentUser currentUser = CurrentUserUtil.getUser(model);
//        Customer currentCustomer =  customerRepository.findByUser(currentUser.getUser());
//        Cart customersCart = cartRepository.findByCustomer(currentCustomer);

        userService.create(form, (Cart) session.getAttribute("cart"));
        return "redirect:/login";
    }

    @RequestMapping(value = "/registration", method = {RequestMethod.POST, RequestMethod.GET})
    public String showRegistrationForm(Model model) {
        model.addAttribute("myform", new UserCreateForm());
        return "registration";
    }

}