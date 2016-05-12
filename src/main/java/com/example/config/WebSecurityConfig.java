package com.example.config;

import com.example.utils.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//    @Autowired
//    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Bean
    AuthFailureHandler authFailureHandler(){
        return new AuthFailureHandler();
    }

    @Bean
    AuthSuccessHandler authSuccessHandler(){
        return new AuthSuccessHandler();
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;

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
//                .httpBasic()//.disable()

                .authorizeRequests()
                .antMatchers("/","/register","/registrationConfirm","/resetPassword","/login**",
                        "/app/**","/views/**","/i18n/**",
                        "/loginA.html","/index.html","/404.html","/meni.html","/registracija.html","/i18n/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .isCustomLoginPage()
                .loginProcessingUrl("/login")
//                    .passwordParameter("password")
//                    .usernameParameter("username")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
//                .and()
//                .logout()
                .and()
// .csrf()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
//                .csrfTokenRepository(csrfTokenRepository())
////                .and().headers().frameOptions().disable().and()
//                .and()
//                .addFilterAfter(new CsrfHeaderFilter(),CsrfFilter.class);

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

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        inMemoryConfigurer()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("USER","ADMIN")
                .and().configure(auth);

        auth.authenticationProvider(authenticationProvider());

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

//    @Component
//    public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
//        private final ObjectMapper mapper;
//
//        @Autowired
//        AuthSuccessHandler(MappingJackson2HttpMessageConverter messageConverter){
//            this.mapper = messageConverter.getObjectMapper();
//        }
//
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
////            super.onAuthenticationSuccess(request, response, authentication);
//            response.setStatus(HttpServletResponse.SC_OK);
//
//
//        }
//
//    }


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
