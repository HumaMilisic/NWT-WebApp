package com.example.utils.events;

import com.example.models.Korisnik;
import com.example.utils.service.CustomUserDetailsService;
import com.example.utils.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event){
        System.out.println("event stigao");
        Korisnik korisnik = event.getKorisnik();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(korisnik,token);
        try{
            emailService.sendVerificationTokenMail(korisnik,event.getAppUrl(),token);
        }
        catch (Exception e){

        }
    }
}
