package com.example.utils.service;

import com.example.models.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${my.mail.sender}")
    private String sender;

    @Value("${my.url}")
    private String adr;

    public void sendMail(String to,String subject, String text){
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(sender);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        try{
            mailSender.send(email);
            System.out.println("mail poslan");
        }catch (Exception e){
            System.out.println(e.toString());
//            throw new Exception("mail umro");
        }
    }

    public void sendResetTokenMail(String to, String appUrl, String token){
        String url = appUrl + "/resetPassword?token=" + token;
        String subject = "Password reset";
        String text = "rn "+adr + url;
        sendMail(to,subject,text);
    }

    public void sendVerificationTokenMail(Korisnik korisnik, String appUrl, String token){
        String to = korisnik.getEmail();
        String subject = "Registration confirmation";
        String confirmationUrl = appUrl + "#/login/registracija/token=" + token;
        String text = " rn " + adr + confirmationUrl;
        sendMail(to,subject,text);
    }

    public void resendVerificationTokenMail(String mail, String appUrl, String token){
        String to = mail;
        String subject = "Resent registration confirmation";
        String confirmationUrl = appUrl + "#/login/registracija/tokenr=" + token;
        String text = " rn " + adr + confirmationUrl;
        sendMail(to,subject,text);
    }

    public void sendNewPasswordMail(String to, String password){
        String subject = "New Password";
        String text = password;
        sendMail(to,subject,text);
    }
}
