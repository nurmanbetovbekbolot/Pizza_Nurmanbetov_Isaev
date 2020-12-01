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
import whz.pti.eva.praktikum_03.dto.UserDTO;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;
import whz.pti.eva.praktikum_03.security.service.user.UserService;
import whz.pti.eva.praktikum_03.security.service.validator.UserCreateFormValidator;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService,UserCreateFormValidator userCreateFormValidator) {
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
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        model.addAttribute("loggedInUser",currentUser);
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

//    @PreAuthorize("#id==principal.id or hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/users/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String getUserPage(@PathVariable Long id, Model model) {
        log.debug("Getting user page for user= " + id);
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        model.addAttribute("loggedInUser",currentUser);
        UserDTO userDTO = userService.getUserById(id);
        model.addAttribute("user", userDTO);
        model.addAttribute("fromUser", userDTO.getLoginName());
        return "user";
    }
    

    @RequestMapping(value = "/users/managed", method = {RequestMethod.POST,RequestMethod.GET}) 
    public String getUserManagedPage(Model model) {
        log.debug("Getting user create form");
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        model.addAttribute("loggedInUser",currentUser);
        model.addAttribute("myform", new UserCreateForm());
        model.addAttribute("users", userService.getAllUsers());
        return "user_create";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        log.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        String currentUser = CurrentUserUtil.getCurrentUser(model);
        model.addAttribute("loggedInUser",currentUser);
        model.addAttribute("users", userService.getAllUsers());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }
            userService.create(form);
//            chatUserService.createChatUser(form);
        return "redirect:/users/managed";
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleCustomerCreate(@Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        log.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }
        userService.create(form);
        return "redirect:/login";
    }

    @RequestMapping(value = "/registration", method = {RequestMethod.POST,RequestMethod.GET})
    public String showRegistrationForm(Model model) {
        model.addAttribute("myform", new UserCreateForm());
        return "registration";
    }

}
