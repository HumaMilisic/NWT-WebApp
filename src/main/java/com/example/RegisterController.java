package com.example;


import com.example.models.Korisnik;
import com.example.models.PasswordResetToken;
import com.example.models.VerificationToken;
import com.example.repo.KorisnikRepository;
import com.example.utils.GenericResponse;
import com.example.utils.GlobalStuff;
import com.example.utils.KorisnikDTO;
import com.example.utils.events.OnRegistrationCompleteEvent;
import com.example.utils.service.CustomUserDetailsService;
import com.example.utils.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by WorkIt on 26/03/2016.
 * http://www.baeldung.com/registration-verify-user-by-email
 */
@RestController
public class RegisterController {


    @Autowired
    CustomUserDetailsService userDetailsService;// = new CustomUserDetailsService();
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    private MessageSource messages;
    @Autowired
    private EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    KorisnikRepository korisnikRepository;

    public RegisterController(){
        super();
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView showRegistrationForm(WebRequest request, ModelAndView mav){
        KorisnikDTO korisnikDTO = new KorisnikDTO();
        mav.setViewName("register");
        mav.addObject("user",korisnikDTO);
        return mav;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid KorisnikDTO korisnikDTO, BindingResult result,
                                            WebRequest request, Errors errors){

        if(result.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("register","user",korisnikDTO);
//            modelAndView.ad
            return modelAndView;
        }

        Korisnik registered = createUserAccount(korisnikDTO,result);

        if(registered==null){
            result.rejectValue("email","message.regError");
        }
        try{
            String appUrl = request.getContextPath();

            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,
                    request.getLocale(),appUrl));
        }catch (Exception ex){
            return new ModelAndView("emailError","user",korisnikDTO);
        }
        return new ModelAndView("successRegister","user",korisnikDTO);
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration (WebRequest request, Model model,
                                       @RequestParam("token") String token){
        Locale locale = request.getLocale();
        VerificationToken verificationToken = userDetailsService.getVerificationToken(token);

        if(verificationToken == null){
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message",message);
            return new ModelAndView("redirect:/badUser");//?lang=" + locale.getLanguage();
        }

        Korisnik user = verificationToken.getKorisnik();
        Calendar cal = Calendar.getInstance();
        double razlika = (verificationToken.getExpiryDate().getTime() - cal.getTime().getTime());
        if (razlika <= 0) {
            model.addAttribute("message", "istekao token");//messages.getMessage("auth.message.expired", null, locale));
            model.addAttribute("expired",true);
            model.addAttribute("token",token);
            return new ModelAndView("redirect:/badUser");
        }

        user.setEnabled(true);
        userDetailsService.saveRegisteredUser(user);
        System.out.println("sacuvan korisnik");
        return new ModelAndView("redirect:/login");//?lang=" + request.getLocale().getLanguage();
    }

    @RequestMapping(value = "/resendRegistrationToken",method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resendRegistrationToken(
            HttpServletRequest request, @RequestParam("token") String existingToken){
        VerificationToken newToken = userDetailsService.generateNewVerificationToken(existingToken);

        Korisnik korisnik = userDetailsService.getKorisnik(newToken.getToken());

        String appUrl = request.getContextPath();

        emailService.resendVerificationTokenMail(korisnik.getEmail(),appUrl,newToken.getToken());

        return new GenericResponse("meh");
    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse resetPassword( HttpServletRequest request,@RequestParam("email") String userEmail) throws Exception{
        Korisnik korisnik = userDetailsService.findUserByMail(userEmail);
        if(korisnik == null){
            throw new UserPrincipalNotFoundException(userEmail);
        }

        String token = UUID.randomUUID().toString();
        userDetailsService.createPasswordResetTokenForUser(korisnik,token);
        String appUrl = request.getContextPath();
        emailService.sendResetTokenMail(korisnik.getEmail(),appUrl,token);
        return new GenericResponse("poruka");
    }

    @RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
    public String showChangePasswordPage(HttpServletRequest request,@RequestParam("token") String token)throws Exception{
        PasswordResetToken passToken = userDetailsService.getPasswordResetToken(token);
        Korisnik korisnik = passToken.getKorisnik();
        if(passToken == null){
            // nejma cojk
        }
        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
            return "redirect:/bad.html";//?lang=" + locale.getLanguage();
        }
//        Authentication auth = new UsernamePasswordAuthenticationToken(korisnik,null,
//                userDetailsService.loadUserByUsername(korisnik.getUsername()).getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
        String noviPassword = GlobalStuff.RandomPassword();
        korisnik.setPassword(passwordEncoder.encode(noviPassword));//passwordEncoder.encode(korisnikDTO.getPassword()
        korisnikRepository.save(korisnik);
        emailService.sendNewPasswordMail(korisnik.getEmail(),noviPassword );
        return "redirect: /login";
    }

    private Korisnik createUserAccount(KorisnikDTO korisnikDTO, BindingResult result ){
        Korisnik registered = null;
        try {
                registered = userDetailsService.registerNewUserAccount(korisnikDTO);
        }catch (Exception e){
            return null;
        }
        return registered;
    }

}
