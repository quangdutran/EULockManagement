package com.dutq.lock.management.lock_management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
  private final UserAuthenticationProvider userAuthenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(userAuthenticationEntryPoint))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            requests ->
                requests
                    .requestMatchers(HttpMethod.POST, "/api/login")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/healthCheck", "/")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class);
    return http.build();
  }
}
