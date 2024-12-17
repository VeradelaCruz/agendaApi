package com.example.agendaApi.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // Define las rutas protegidas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll() // Rutas públicas
                        .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
                )
                .httpBasic(config -> {}); // Configuración explícita de httpBasic

        return http.build();
    }
}





