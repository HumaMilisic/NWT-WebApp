package com.example.config;


import com.example.utils.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by WorkIt on 12/03/2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //.httpBasic().and()
                .authorizeRequests()
                    .antMatchers("/*","/register","/registrationConfirm","/user/resetPassword","/console/*","/api")
                .permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
//                    .loginProcessingUrl("/login")

                    .loginPage("/login")
                    .permitAll().and()
                .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
                .and().headers().frameOptions().disable()
                .and().csrf().disable();
//                .and()
//                .headers().frameOptions().disable();


    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        inMemoryConfigurer()
                .withUser("admin").password("admin").roles("ADMIN","USER")
        .and().configure(auth);

        auth
        //.jdbcAuthentication()
                //.and()
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
//                .and()
//                .inMemoryAuthentication()
//                    .withUser("user").password("user").roles("USER")
//                .and()




    }

    private InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
    inMemoryConfigurer(){
        return new InMemoryUserDetailsManagerConfigurer<>();
    }
//    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter{
//        @Autowired
//        private CustomUserDetailsService userDetailsService;
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception{
//            auth.userDetailsService(userDetailsService);
//        }
//    }
}
