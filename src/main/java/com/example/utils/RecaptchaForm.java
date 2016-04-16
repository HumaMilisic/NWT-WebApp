package com.example.utils;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by WorkIt on 16/04/2016.
 * https://github.com/bkielczewski/example-spring-boot-recaptcha/blob/recaptcha/src/main/java/eu/kielczewski/example/domain/form/RecaptchaForm.java
 */
public abstract class RecaptchaForm {

    @NotEmpty
    @NotNull
    private String recaptchaResponse;

    public String getRecaptchaResponse() {
        return recaptchaResponse;
    }

    public void setRecaptchaResponse(String recaptchaResponse) {
        this.recaptchaResponse = recaptchaResponse;
    }

    @Override
    public String toString() {
        return "RecaptchaForm{" +
                "recaptchaResponse='" + recaptchaResponse + '\'' +
                '}';
    }
}
