package com.university.repairagency.registration.validation;

import com.university.repairagency.password.PasswordService;
import com.university.repairagency.password.annotation.PasswordMatches;
import com.university.repairagency.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class RegistrationPasswordValidator implements ConstraintValidator<PasswordMatches, Object> {

    private PasswordService passwordService;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        RegistrationRequest registrationRequest = (RegistrationRequest) obj;
        boolean isValid = isValid(registrationRequest);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("matchingPassword").addConstraintViolation();
        }

        return isValid;
    }

    private boolean isValid(RegistrationRequest registrationRequest) {
        return passwordService.isMatches(registrationRequest.getPassword(), registrationRequest.getMatchingPassword());
    }
}
