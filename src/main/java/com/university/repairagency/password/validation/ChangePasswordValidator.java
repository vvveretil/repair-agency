package com.university.repairagency.password.validation;

import com.university.repairagency.password.PasswordChangeRequest;
import com.university.repairagency.password.PasswordService;
import com.university.repairagency.password.annotation.PasswordMatches;
import com.university.repairagency.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class ChangePasswordValidator implements ConstraintValidator<PasswordMatches, Object> {

    private PasswordService passwordService;

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        PasswordChangeRequest passwordChangeRequest = (PasswordChangeRequest) obj;
        User user = passwordChangeRequest.getUser();

        return passwordService.isEncryptedMatches(user.getPassword(), passwordChangeRequest.getOldPassword());
    }
}
