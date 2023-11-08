package dev.tojoos.helpnow.config;

import dev.tojoos.helpnow.util.EmailUtil;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class used for mail details.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-01-16
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {
  @Value("${mail.host}")
  private String host;

  @Value("${mail.port}")
  private int port;

  @Value("${mail.smtp.auth}")
  private boolean auth;

  @Value("${mail.smtp.starttls.enable}")
  private boolean starttls;

  @Value("${mail.smtp.ssl.protocols}")
  private String protocols;

  @Value("${mail.username}")
  private String username;

  @Value("${mail.password}")
  private String password;

  @Bean
  public EmailUtil emailUtil() {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);
    properties.put("mail.smtp.auth", auth);
    properties.put("mail.smtp.starttls.enable", starttls);
    properties.put("mail.smtp.ssl.protocols", protocols);

    return new EmailUtil(username, password, properties);
  }
}