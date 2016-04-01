package com.example.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WorkIt on 26/03/2016.
 */
@Entity
public class VerificationToken {
    @Value("${my.token.trajanje}")
    private static int trajanje;
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Korisnik.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "korisnik")
    private Korisnik korisnik;

    private Date expiryDate;

    private boolean verified;

    public VerificationToken(){
        super();
    }

    public VerificationToken(String token, Korisnik korisnik){
        super();
        this.token = token;
        this.korisnik = korisnik;
        this.verified = false;
        this.expiryDate = calculateExpiryDate(this.EXPIRATION);
    }

    public void updateToken(String token){
        this.token = token;
        this.expiryDate = calculateExpiryDate(this.EXPIRATION);
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE,expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }
}
