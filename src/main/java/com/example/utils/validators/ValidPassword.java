package com.example.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by WorkIt on 27/03/2016.
 * http://www.baeldung.com/registration-password-strength-and-rules
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
