package dev.tojoos.helpnow.security;

import static org.springframework.http.HttpMethod.*;

import dev.tojoos.helpnow.filters.CustomAuthenticationFilter;
import dev.tojoos.helpnow.filters.CustomAuthorizationFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration class. It configures cors, access to several
 * application's mappings, authenticate users.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-27
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
    customAuthenticationFilter.setFilterProcessesUrl("/login");

    httpSecurity.csrf().disable();
    httpSecurity.cors();

    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.authorizeRequests().antMatchers(GET, "/fundraise/**", "/statistics/**",
                "/announcement/**", "/organization/**", "/employee/**").permitAll();
    httpSecurity.authorizeRequests().antMatchers(OPTIONS, "/**").permitAll();
    httpSecurity.authorizeRequests().antMatchers(PUT, "/statistics/**").permitAll();
    httpSecurity.authorizeRequests().antMatchers(POST, "/user/add").permitAll();
    httpSecurity.authorizeRequests().antMatchers("/login", "/user/token/refresh", "/mail/contact").permitAll();

    //user accesses
    httpSecurity.authorizeRequests().antMatchers(POST, "/announcement/**").hasAnyAuthority("USER", "ADMIN");
    httpSecurity.authorizeRequests().antMatchers(PUT, "/fundraise/**").hasAnyAuthority("USER", "ADMIN");
    httpSecurity.authorizeRequests().antMatchers(GET, "/employee/**").hasAnyAuthority("ADMIN");

    //admin accesses here
    httpSecurity.authorizeRequests().antMatchers(POST, "/statistics/**",
                 "/organization/**", "/employee/**", "/user/**").hasAnyAuthority("ADMIN");
    httpSecurity.authorizeRequests().antMatchers(PUT,
                "/announcement/**", "/organization/**", "/employee/**", "/user/**").hasAnyAuthority("ADMIN");
    httpSecurity.authorizeRequests().antMatchers(DELETE, "/employee/**").hasAnyAuthority("ADMIN");

    httpSecurity.authorizeRequests().anyRequest().authenticated();

    httpSecurity.addFilter(customAuthenticationFilter);
    httpSecurity.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:4200"));
    configuration.setAllowedMethods(List.of("HEAD",
        "GET", "POST", "PUT", "DELETE", "PATCH"));
    // setAllowCredentials(true) is important, otherwise:
    // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
    configuration.setAllowCredentials(true);
    // setAllowedHeaders is important! Without it, OPTIONS preflight request
    // will fail with 403 Invalid CORS request
    configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}