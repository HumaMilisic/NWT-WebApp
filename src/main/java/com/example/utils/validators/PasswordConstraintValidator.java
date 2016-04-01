package com.example.utils.validators;

import com.example.utils.GlobalStuff;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(
                GlobalStuff.getRules());
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
