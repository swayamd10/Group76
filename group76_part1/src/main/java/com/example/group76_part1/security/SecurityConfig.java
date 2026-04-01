package com.example.group76_part1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Injecting the passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Creating a Data Access Object (DAO) which will provide the user service with the password
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService); // ✅ required now
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Injecting the AuthenticationManager if it is needed
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())

                // Disabling CSRF as this is not required for an API
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        // Both HR and MANAGER can use the GET method
                        .requestMatchers(HttpMethod.GET,    "/api/**").hasAnyRole("MANAGER", "HR")

                        // Only HR can use the POST, PUT, PATCH, DELETE methods
                        .requestMatchers(HttpMethod.POST,   "/api/**").hasRole("HR")
                        .requestMatchers(HttpMethod.PUT,    "/api/**").hasRole("HR")
                        .requestMatchers(HttpMethod.PATCH,  "/api/**").hasRole("HR")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("HR")

                        // Do not allow any other requests
                        .anyRequest().authenticated()
                )

                // Will return a 401 Unauthorized error for an unauthenticated user accessing the API
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )

                // Allows user to send user credentials in the header of each API request
                .httpBasic(basic -> {});

        return http.build();
    }
}
