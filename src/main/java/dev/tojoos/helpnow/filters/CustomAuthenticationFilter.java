package dev.tojoos.helpnow.filters;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Filter that authenticates user by validating a password and a login. It also creates a JWT.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-27
 */
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final String jwtSecret = "your_secret_here";

  private final AuthenticationManager authenticationManager;

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @SneakyThrows
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String username;
    String password;

    if (Objects.equals(request.getContentType(), APPLICATION_JSON_VALUE)) {
      // Read the request body into a string
      BufferedReader reader = request.getReader();
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
      String requestBody = sb.toString();

      // Parse the request body as a JSON object
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(requestBody);

      // Extract the username and password from the request body
      username = rootNode.get("username").asText();
      password = rootNode.get("password").asText();
    } else {
      username = request.getParameter("username");
      password = request.getParameter("password");
    }

    log.info("Username={}, Password=*****", username);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
    User user = (User) authentication.getPrincipal();
    Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    String accessToken = JWT.create()
          .withSubject(user.getUsername())
          .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) //30 min token
          .withIssuer(request.getRequestURL().toString())
          .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
          .sign(algorithm);

    String refreshToken = JWT.create()
          .withSubject(user.getUsername())
          .withExpiresAt(new Date(System.currentTimeMillis() + 12 * 60 * 60 * 1000)) //12 h token
          .withIssuer(request.getRequestURL().toString())
          .sign(algorithm);

    Map<String, String> tokens = new HashMap<>();
    tokens.put("access_token", accessToken);
    tokens.put("refresh_token", refreshToken);

    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  }
}
