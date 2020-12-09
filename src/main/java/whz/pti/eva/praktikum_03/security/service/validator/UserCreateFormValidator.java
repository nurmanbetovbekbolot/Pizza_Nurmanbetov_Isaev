package whz.pti.eva.praktikum_03.security.service.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import whz.pti.eva.praktikum_03.security.domain.UserCreateForm;
import whz.pti.eva.praktikum_03.security.service.user.UserService;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password", "unterschiedliche passwoerter eingegeben! vertippt?");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        if (userService.existByLoginName(form.getLoginName())) {
            errors.reject("loginName", "nutzer mit diesem loginName existiert bereits !!!");
        }
        else
        if (userService.existsByEmail(form.getEmail())) {
            errors.reject("email", "nutzer mit dieser email existiert bereits !!!");
        }
    }
}
