package com.bits.g7.civicconnect.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabled for local testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/complaints").permitAll() // Citizens can submit
                        .requestMatchers("/api/v1/complaints/track/**").permitAll() // Citizens can track
                        .requestMatchers("/api/v1/complaints/**").hasRole("OFFICER") // Only Officers can update
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Basic Auth for Officer Dashboard login
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails officer = User.builder()
                .username("officer1")
                .password(passwordEncoder().encode("admin123"))
                .roles("OFFICER")
                .build();
        return new InMemoryUserDetailsManager(officer);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}