package com.example.controller;

import com.example.models.Korisnik;
import com.example.repo.KorisnikRepository;
import com.example.utils.GenericResponse;
import com.example.utils.GlobalStuff;
import com.example.utils.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResetPasswordController {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ResetPasswordController(){super();}

    @RequestMapping(value = "/user/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GenericResponse> ressetPassword(@RequestParam("username") String username,@RequestParam("email") boolean sendEmail){
        Korisnik korisnik = korisnikRepository.findByUsername(username);
        GenericResponse uspjeh = new GenericResponse("");
        GenericResponse fail = new GenericResponse("","");
        if(korisnik == null){
            fail.setMessage("korisnik ne postoji");
            return new ResponseEntity<GenericResponse>(fail, HttpStatus.EXPECTATION_FAILED);
        }
        try{
            String noviPassword = GlobalStuff.RandomPassword();
            korisnik.setPassword(passwordEncoder.encode(noviPassword));//passwordEncoder.encode(korisnikDTO.getPassword()
            korisnikRepository.save(korisnik);
            uspjeh.setMessage("password promjenjen");
            if(sendEmail){
                emailService.sendNewPasswordMail(korisnik.getEmail(),noviPassword );
                uspjeh.setMessage("mail poslan");
            }
            return new ResponseEntity<GenericResponse>(uspjeh,HttpStatus.OK);
        }catch (Exception e){
            fail.setMessage("exception");
            fail.setObjekat(e);
            return new ResponseEntity<GenericResponse>(fail, HttpStatus.EXPECTATION_FAILED);
        }

    }
}