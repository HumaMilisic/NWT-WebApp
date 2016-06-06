//package com.example.utils.validators;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
///**
// * Created by WorkIt on 27/03/2016.
// * jer oracle je govno
// */
//public class BoolValidator implements ConstraintValidator<ValidBool,String> {
//
//    @Override
//    public void initialize(ValidBool constraintAnnotation) {
//
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return validateBool(value);
//    }
//
//    private boolean validateBool(String bool){
//        return bool=="1" || bool=="0";
//    }
//}
