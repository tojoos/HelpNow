package dev.tojoos.helpnow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class that serves as starting point of an application.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-10-30
 */
@SpringBootApplication
public class HelpNowApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelpNowApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
