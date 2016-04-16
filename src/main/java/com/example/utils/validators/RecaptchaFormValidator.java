package com.example.utils.validators;


import com.example.utils.RecaptchaForm;
import com.example.utils.exceptions.RecaptchaServiceException;
import com.example.utils.service.ReCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;


//http://kielczewski.eu/2015/07/spring-recaptcha-v2-form-validation/


@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RecaptchaFormValidator implements org.springframework.validation.Validator{

    private static final String ERROR_RECAPTCHA_INVALID = "recaptcha.error.invalid";
    private static final String ERROR_RECAPTCHA_UNAVAILABLE = "recaptcha.error.unavailable";
    private final HttpServletRequest httpServletRequest;
    private final ReCaptchaService recaptchaService;

    @Autowired
    public RecaptchaFormValidator(HttpServletRequest httpServletRequest,ReCaptchaService recaptchaService){
        this.httpServletRequest = httpServletRequest;
        this.recaptchaService = recaptchaService;
    }

    @Override
    public boolean supports(Class<?> clazz){
        return RecaptchaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        RecaptchaForm form = (RecaptchaForm) target;
        try {
            if(form.getRecaptchaResponse() != null
                    && !form.getRecaptchaResponse().isEmpty()
                    && !recaptchaService.isResponseValid(getRemoteIp(httpServletRequest),
                    form.getRecaptchaResponse())){
                errors.reject(ERROR_RECAPTCHA_INVALID);
                errors.rejectValue("recaptchaResponse", ERROR_RECAPTCHA_INVALID);
            }
        }catch (RecaptchaServiceException e){
            errors.reject(ERROR_RECAPTCHA_UNAVAILABLE);
        }
    }

    private String getRemoteIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
