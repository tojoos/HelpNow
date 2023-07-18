package dev.tojoos.helpnow.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class responsible for enabling Cross-origin resource sharing.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-27
 */
@Configuration
public class WebConfig implements WebMvcConfigurer  {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
  }
}