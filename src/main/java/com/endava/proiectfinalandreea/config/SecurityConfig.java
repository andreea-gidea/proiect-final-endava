package com.endava.proiectfinalandreea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
//        http.authorizeRequests()
//                .mvcMatchers("/supplier").hasAnyRole("CLIENT")
//                .anyRequest()
//                .authenticated();

//        http.csrf()
//                .ignoringAntMatchers("/users")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/users").permitAll();

        http.csrf().disable();
        http.authorizeRequests()
//                .mvcMatchers("/user/get-all").hasRole("ADMIN")
//                .mvcMatchers("/user/supplier").hasRole("ADMIN")
                .mvcMatchers("/orders/create-order").hasAnyRole("ADMIN" ,"CLIENT","COMPANY_CLIENT")
//                .mvcMatchers("/user/admin").permitAll()
//                .mvcMatchers("/user/individual-client").permitAll()
//                .mvcMatchers("/user/company-client").permitAll()
                .anyRequest()
                .authenticated();
    }
}
