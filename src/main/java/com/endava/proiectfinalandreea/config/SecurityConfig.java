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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();


        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/get-all").hasRole("ADMIN")
                .antMatchers("/user/supplier").hasRole("ADMIN")
                .antMatchers("/user/admin").hasRole("ADMIN")
                .antMatchers("/orders/create-order").hasAnyRole("CLIENT", "COMPANY_CLIENT")
                .antMatchers("/orders/get-my-orders/**").hasAnyRole("CLIENT", "COMPANY_CLIENT")
                .antMatchers("/orders/get-new-orders").hasRole("SUPPLIER")
                .antMatchers("/orders/get-all-orders-assigned-to-supplier").hasRole("SUPPLIER")
                .antMatchers("/orders/get-all-orders").hasRole("ADMIN")
                .antMatchers("/orders/change-status/**").hasRole("SUPPLIER")
                .antMatchers("/user/individual-client").permitAll()
                .antMatchers("/user/company-client").permitAll()
                .anyRequest()
                .authenticated();
    }
}
