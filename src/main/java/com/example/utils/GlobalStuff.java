package com.example.utils;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.security.Principal;
import java.util.Arrays;

/**
 * Created by WorkIt on 04/04/2016.
 */
public class GlobalStuff {



//    @Autowired
//    private CustomUserDetailsService service;
//    @Autowired
//    private KorisnikRepository repository;
//
//    public GlobalStuff(){
//        repository = new KorisnikRepository();
//        service = new CustomUserDetailsService(repository);
//    }
//    @Autowired
//    static private KorisnikRepository korisnikRepository;
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

    public static Principal user(Principal user){
        return user;
    }

//
//    public Korisnik getPrincipalKorisnikFromRepo()
//    {
//        try{
//            Korisnik user = service.getCurrentPrincipalKorisnik();
//            return user;
//        }
//        catch (Exception e){
//            return null;
//        }
////        return service.getCurrentPrincipalKorisnik();
//    }

}
