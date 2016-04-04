package com.example.utils.validators;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(8,30),
                        new CharacterRule(EnglishCharacterData.UpperCase,1),
                        new CharacterRule(EnglishCharacterData.Digit,1),
                        new CharacterRule(EnglishCharacterData.Special,1)//,
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
