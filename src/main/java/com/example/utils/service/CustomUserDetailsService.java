package com.example.utils.service;


import com.example.models.Korisnik;
import com.example.models.PasswordResetToken;
import com.example.models.VerificationToken;
import com.example.repo.KorisnikRepository;
import com.example.repo.PasswordResetTokenRepository;
import com.example.repo.VerificationTokenRepository;
import com.example.utils.KorisnikDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
            if(korisnik == null){
                return null;
            }
            List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER");

            String password = korisnik.getPassword();

            return new org.springframework.security.core.userdetails.User(
                    korisnik.getUsername(),
                    korisnik.getPassword(),
                    korisnik.isEnabled(),
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
        user.setDatumRodjenja(LocalDate.now());
        user.setEmail(korisnikDTO.getEmail().toLowerCase());
        user.setIme(korisnikDTO.getIme());
        user.setPrezime(korisnikDTO.getPrezime());
        user.setUsername(korisnikDTO.getUsername());
        user.setPassword(passwordEncoder.encode(korisnikDTO.getPassword()));
        return repo.save(user);
    }

    private boolean emailExists(String email){
        Korisnik korisnik = repo.findByEmail(email);
        if(korisnik != null){
            return true;
        }
        return false;
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
}
