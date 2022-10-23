package com.university.repairagency.password.annotation;

import com.university.repairagency.password.validation.ChangePasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(value = {TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ChangePasswordValidator.class)
@Documented
public @interface OldPasswordMatches {
    String message() default "Old password is wrong!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}