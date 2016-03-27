package com.example.repo;


import com.example.models.Korisnik;
import com.example.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository  extends JpaRepository<VerificationToken,Long>{
    VerificationToken findByToken(String token);
    VerificationToken findByKorisnik(Korisnik korisnik);
}
