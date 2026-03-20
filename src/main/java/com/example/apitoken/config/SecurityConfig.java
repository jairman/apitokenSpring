package com.example.apitoken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitado para poder usar Postman fácilmente
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/tasks/**").authenticated() // Protege los endpoints
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults()); // Usa autenticación básica (User/Pass)

        return http.build();
    }

}
