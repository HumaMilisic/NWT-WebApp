package com.example.utils.exceptions;

/**
 * Created by WorkIt on 16/04/2016.
 * https://github.com/bkielczewski/example-spring-boot-recaptcha/blob/recaptcha/src/main/java/eu/kielczewski/example/service/recaptcha/exception/RecaptchaServiceException.java
 */
public class RecaptchaServiceException extends RuntimeException {
    public RecaptchaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
