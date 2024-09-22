package com.dutq.lock.management.lock_management.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

  private static final Long MAX_AGE = 3600L;
  private static final int CORS_FILTER_ORDER = -102;

  @Value("cors.allowed.origins")
  private String corsAllowedOrigins;

  @Bean
  public FilterRegistrationBean corsFilterConfig() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    String[] allowedOrigins = corsAllowedOrigins.split(",");
    config.setAllowedOrigins(
        Arrays.asList(allowedOrigins));
    config.setAllowedHeaders(
        Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
    config.setAllowedMethods(
        Arrays.asList(
            HttpMethod.GET.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name()));
    config.setMaxAge(MAX_AGE);
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

    // should be set order to -100 because we need to CorsFilter before SpringSecurityFilter
    bean.setOrder(CORS_FILTER_ORDER);
    return bean;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return new RestTemplate();
  }
}
