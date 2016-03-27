package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by WorkIt on 12/03/2016.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/hello").setViewName("hello");
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
//        registry
        registry.addViewController("/registrationConfirm").setViewName("registrationConfirm");
//        registry.addViewController("/register").setViewName("register");

//        registry.addViewController("/hello").setViewName("hello");
    }
}
