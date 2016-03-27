package com.example.utils.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by WorkIt on 27/03/2016.
 * interfaceee
 */

@Target({ElementType.TYPE,ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BoolValidator.class)
@Documented
public @interface ValidBool {
    String message() default "Invalid bool";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
