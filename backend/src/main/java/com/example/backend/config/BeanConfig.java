package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BeanConfig {
    @Bean
    public static PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
