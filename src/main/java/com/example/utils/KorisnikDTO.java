package com.example.utils;

import com.example.utils.validators.PasswordMatches;
import com.example.utils.validators.ValidPassword;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class KorisnikDTO extends RecaptchaForm{

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String ime;

    @NotEmpty
    @NotNull
    @NotBlank
    @Size(max = 20)
    private String prezime;

    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotEmpty
    @NotNull
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

}
