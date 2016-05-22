package com.example.utils.service;


import com.example.models.*;
import com.example.repo.KorisnikRepository;
import com.example.repo.PasswordResetTokenRepository;
import com.example.repo.VerificationTokenRepository;
import com.example.utils.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by WorkIt on 25/03/2016.
 * https://github.com/scratches/jpa-method-security-sample/blob/master/src/main/java/demo/Application.java
 * https://github.com/eugenp/spring-security-registration/blob/master/src/main/java/org/baeldung/persistence/service/UserService.java
 */

@Service
//@Transactional
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private KorisnikRepository repo;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepo;


    @Autowired
    public CustomUserDetailsService(KorisnikRepository repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        try{
            Korisnik korisnik = repo.findByUsername(username);
            Set<Uloga> setUloga = korisnik.getUlogaSet();
            Set<Akcija> setAkcija = new HashSet<Akcija>();
            setUloga.forEach((uloga -> {
                setAkcija.addAll(uloga.getAkcijaSet());
            }));
            if(korisnik == null){
                return null;
            }
            String lista = "";
            for(Akcija i: setAkcija){
                lista+= i.getNaziv()+",";
            }
            List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(lista);//ROLE_ADMIN,
            AuthorityUtils.createAuthorityList();
            String password = korisnik.getPassword();

            Boolean isEnabled = false;
            if(korisnik.isEnabled()=="1"){
                isEnabled = true;
            }

            return new org.springframework.security.core.userdetails.User(
                    korisnik.getUsername(),
                    korisnik.getPassword(),
                    isEnabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    auth);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Korisnik registerNewUserAccount(KorisnikDTO korisnikDTO) throws Exception{
        if(emailExists(korisnikDTO.getEmail())){
            throw new Exception();
        }
        Korisnik user = new Korisnik();
        user.setDatumRodjenja(new Date());
        user.setEmail(korisnikDTO.getEmail().toLowerCase());
        user.setIme(korisnikDTO.getIme());
        user.setPrezime(korisnikDTO.getPrezime());
        user.setUsername(korisnikDTO.getUsername());
        user.setPassword(passwordEncoder.encode(korisnikDTO.getPassword()));
        user.setEnabled("0");
        return repo.save(user);
    }

    private boolean emailExists(String email){
        Korisnik korisnik = repo.findByEmail(email);
        return korisnik != null;
    }

    public Korisnik getKorisnik(String verificationToken){
        Korisnik korisnik = tokenRepository.findByToken(verificationToken).getKorisnik();
        return korisnik;
    }

    public Korisnik findUserByMail(String mail){
        return repo.findByEmail(mail);
    }

    public VerificationToken getVerificationToken(String verificationToken){
        return tokenRepository.findByToken(verificationToken);
    }

    public PasswordResetToken getPasswordResetToken(String token){
        return passwordResetTokenRepo.findByToken(token);
    }

    public void saveRegisteredUser(Korisnik korisnik){
        repo.save(korisnik);
    }

    public void createVerificationToken(Korisnik korisnik, String token){
        VerificationToken myToken = new VerificationToken(token,korisnik);
        tokenRepository.save(myToken);
    }

    public VerificationToken generateNewVerificationToken(final String existing){
        VerificationToken token = tokenRepository.findByToken(existing);
        token.updateToken(UUID.randomUUID().toString());
        token = tokenRepository.save(token);
        return token;
    }

    public void createPasswordResetTokenForUser(Korisnik korisnik,String token){
        PasswordResetToken myToken = new PasswordResetToken(token,korisnik);
        passwordResetTokenRepo.save(myToken);
    }

    public Korisnik getCurrentPrincipalKorisnik(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Korisnik user = null;
        try {
            user = repo.findByUsername(currentPrincipalName);
        }
        catch (Exception e){
            user = null;
        }
        return user;
    }
}
