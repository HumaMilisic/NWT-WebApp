package com.example.utils.events;

import com.example.models.Korisnik;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final String appUrl;
    private final Locale locale;
    private final Korisnik korisnik;

    public OnRegistrationCompleteEvent(Korisnik korisnik, Locale locale, String appUrl){
        super(korisnik);

        this.korisnik = korisnik;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
}
