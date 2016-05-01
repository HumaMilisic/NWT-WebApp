package com.example.config;

import com.example.utils.filter.CsrfHeaderFilter;
import com.example.utils.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .httpBasic().and()
//                .authorizeRequests().antMatchers("/","/login",
//                "/index.html","/loginA.html")
//                    .permitAll().anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                .and()
//                .logout()
//                .and()
//                    .csrf().disable();
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/","/register","/registrationConfirm","/resetPassword","/login","/app/**","/views/**",
                        "/loginA.html","/index.html","/404.html","/meni.html","/registracija.html","/i18n/**").permitAll()
                .anyRequest().authenticated()
                .and()
//                .logout()
//                .and()
// .csrf()
                .csrf().csrfTokenRepository(csrfTokenRepository()).and()
                .addFilterAfter(new CsrfHeaderFilter(),CsrfFilter.class);

//                .and()
//                .csrf().disable();

//        http
//                //.httpBasic().and()
//                .authorizeRequests()
//                    .antMatchers("/","/register","/registrationConfirm","/resetPassword","#/login")
//                .permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//
//                .formLogin()
////                    .loginProcessingUrl("/login")
//
//                    .loginPage("/login")
//
//                    .permitAll().and()
//                .logout()
//                    .permitAll()
////                    .logoutSuccessUrl("/");
////                .and().headers().frameOptions().disable()
//                .and().csrf().disable()//;
////                .and()
//                .headers().frameOptions().disable();
//        http.exceptionHandling().authenticationEntryPoint()
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        inMemoryConfigurer()
//                .withUser("user").password("user").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("USER","ADMIN")
//                .and().configure(auth);

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
    inMemoryConfigurer(){
        return new InMemoryUserDetailsManagerConfigurer<>();
    }

    private CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}

//
//
//import com.example.utils.filter.CsrfHeaderFilter;
//import com.example.utils.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.csrf.CsrfFilter;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//
///**
// * Created by WorkIt on 12/03/2016.
// */
//@Configuration
//@EnableWebSecurity
//
////@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
//                .httpBasic().and().authorizeRequests()
//                .antMatchers("/","/register","/registrationConfirm","/user/resetPassword","/login","/app/**",
//                        "/loginA.html","/index.html","/404.html","/meni.html","/registracija.html","/i18n/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
////                .logout()
////                .and()
//// .csrf()
//                .csrf().csrfTokenRepository(csrfTokenRepository()).and()
//                .addFilterAfter(new CsrfHeaderFilter(),CsrfFilter.class);
//
////                .and()
////                .csrf().disable();
//
////        http
////                //.httpBasic().and()
////                .authorizeRequests()
////                    .antMatchers("/","/register","/registrationConfirm","/resetPassword","#/login")
////                .permitAll()
////                    .anyRequest().authenticated()
////                    .and()
////
////                .formLogin()
//////                    .loginProcessingUrl("/login")
////
////                    .loginPage("/login")
////
////                    .permitAll().and()
////                .logout()
////                    .permitAll()
//////                    .logoutSuccessUrl("/");
//////                .and().headers().frameOptions().disable()
////                .and().csrf().disable()//;
//////                .and()
////                .headers().frameOptions().disable();
////        http.exceptionHandling().authenticationEntryPoint()
//    }
//
//
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        inMemoryConfigurer()
//                .withUser("admin").password("admin").roles("ADMIN","USER")
//        .and().configure(auth);
//
//        auth
//        //.jdbcAuthentication()
//                //.and()
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder);
////                .and()
////                .inMemoryAuthentication()
////                    .withUser("user").password("user").roles("USER")
////                .and()
//
//
//
//
//    }
//
//    private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
//    inMemoryConfigurer(){
//        return new InMemoryUserDetailsManagerConfigurer<>();
//    }
////    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter{
////        @Autowired
////        private CustomUserDetailsService userDetailsService;
////
////        @Override
////        public void init(AuthenticationManagerBuilder auth) throws Exception{
////            auth.userDetailsService(userDetailsService);
////        }
////    }
//
//    private CsrfTokenRepository csrfTokenRepository(){
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setHeaderName("X-XSRF-TOKEN");
//        return repository;
//    }
//}
//
//
