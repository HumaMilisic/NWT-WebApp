package com.example.utils;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Arrays;

/**
 * Created by WorkIt on 04/04/2016.
 */
public class GlobalStuff {
//    public static List<CharacterRule> getRules(){
////        return new List<CharacterRule>(
////                new LengthRule(8,30),
////                new CharacterRule(EnglishCharacterData.UpperCase,1),
////                new CharacterRule(EnglishCharacterData.Digit,1),
////                new CharacterRule(EnglishCharacterData.Special,1)//,
//////                new UppercaseCharacterRule(1),
//////                new DigitCharacterRule(1),
//////                new SpecialCharacterRule(1),
//////                new WhitespaceRule()
////        );
//    }
    private static PasswordGenerator generator = new PasswordGenerator();

    public static String RandomPassword(){
        String rez = generator.generatePassword(11,Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase,1),
                new CharacterRule(EnglishCharacterData.LowerCase,4),
                new CharacterRule(EnglishCharacterData.Digit,5),
                new CharacterRule(EnglishCharacterData.Special,1)
        ));
        return rez;
    }
}
