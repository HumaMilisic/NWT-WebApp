package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by WorkIt on 27/03/2016.
 * jer tutorijal sere
 */
public class GenericResponse {
    private String message;
    private String error;
    private Object objekat;
    private HttpStatus httpStatus;

    public GenericResponse(final String message){
        super();
        this.message = message;
    }

    public GenericResponse(final String message,final String error,final Object objekat, final  HttpStatus httpStatus){
        super();
        this.message = message;
        this.error = error;
        this.objekat = objekat;
        this.httpStatus = httpStatus;
    }

    public GenericResponse(final String message,final String error,final Object objekat){
        super();
        this.message = message;
        this.error = error;
        this.objekat = objekat;
    }

    public GenericResponse(final String message, final String error){
        super();
        this.message = message;
        this.error = error;
    }

    public GenericResponse(final List<FieldError> fieldErrors, final List<ObjectError> globalErrors) {
        super();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            this.message = mapper.writeValueAsString(fieldErrors);
            this.error = mapper.writeValueAsString(globalErrors);
        } catch (final JsonProcessingException e) {
            this.message = "";
            this.error = "";
        }
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObjekat() {
        return objekat;
    }

    public void setObjekat(Object objekat) {
        this.objekat = objekat;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
