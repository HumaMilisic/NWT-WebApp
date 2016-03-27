package com.example.utils.validators;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by WorkIt on 27/03/2016.
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(8,30),
                        new UppercaseCharacterRule(1),
                        new DigitCharacterRule(1),
                        new SpecialCharacterRule(1),
                        new WhitespaceRule()
                ));
        RuleResult result = validator.validate(new PasswordData(value));
        if(result.isValid()){
            return true;
        }
        return true;
//        context.disableDefaultConstraintViolation();
//        context.buildConstraintViolationWithTemplate(
//                Joiner.on("n").join(validator.getMessages(result)))
//                .addConstraintViolation();
//
//        return false;
    }
}
