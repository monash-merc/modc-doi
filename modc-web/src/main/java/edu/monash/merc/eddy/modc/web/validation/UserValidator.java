package edu.monash.merc.eddy.modc.web.validation;

import edu.monash.merc.eddy.modc.domain.User;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by simonyu on 6/08/2014.
 */
@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.first.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.last.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
        User user = (User) target;

        String email = user.getEmail();
        if (StringUtils.isNotBlank(email) && !validateEmail(email)) {
            errors.rejectValue("email", "invalid.email.address");
        }
    }

    private boolean validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }
}
