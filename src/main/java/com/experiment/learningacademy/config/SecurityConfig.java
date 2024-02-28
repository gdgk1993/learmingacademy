package com.experiment.learningacademy.config;

import com.experiment.learningacademy.security.JWTAuthenticationEntryPoint;
import com.experiment.learningacademy.security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableWebSecurity
@CrossOrigin("")
@Configuration
public class SecurityConfig {

    //conditions
    /*
    cross domain request - cross issue
    CSRF(cross site request forgery)



    */
    @Autowired
    private JWTAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JWTAuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/**").authenticated()
                        .requestMatchers("/login","/register","/confirm/*").permitAll().anyRequest()
                        .authenticated())
                .exceptionHandling(exception->exception.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
