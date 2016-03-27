package com.example.utils.validators;

import com.example.utils.KorisnikDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by WorkIt on 27/03/2016.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        KorisnikDTO korisnikDTO = (KorisnikDTO) value;
        return korisnikDTO.getPassword().equals(korisnikDTO.getMatchingPassword());
    }
}
