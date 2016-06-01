package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//    private final ObjectMapper mapper;

//    @Bean
//    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
//        return new MappingJackson2HttpMessageConverter();
//    }

    @Autowired
    AuthSuccessHandler() {
//        this.mapper = messageConverter.getObjectMapper();
    }

//    @Autowired
//    private IAuthMetricService authMetricService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //            super.onAuthenticationSuccess(request, response, authentication);
         response.setStatus(HttpServletResponse.SC_OK);
//        final String req = request.getMethod()+" "+request.getRequestURI();
//        authMetricService.increaseCount(req,1);
        PrintWriter writer = response.getWriter();
//        mapper.writeValue(writer,);
    }
}