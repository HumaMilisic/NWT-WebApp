package com.example.repo;

import com.example.models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by WorkIt on 27/03/2016.
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByKorisnik(@Param("name") String korisnik);

    PasswordResetToken findByToken(@Param("name") String token);
}
