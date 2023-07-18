package dev.tojoos.helpnow.filters;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter that authorizes a requests by validating JWT token from request header.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-12-27
 */
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
  private final String jwtSecret = "your_secret_here";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //determine if user has access to resource or not
    if (request.getServletPath().equals("/login") || request.getServletPath().equals("/user/token/refresh")) { //do nothing if users just want to log in
      filterChain.doFilter(request, response);
    } else {
      String authorizationHeader = request.getHeader(AUTHORIZATION);
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { //it is token auth
        try {
          String token = authorizationHeader.substring("Bearer ".length());
          Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes()); //same secret that encoded the token
          JWTVerifier verifier = JWT.require(algorithm).build();
          DecodedJWT decodedJwt = verifier.verify(token);
          String username = decodedJwt.getSubject();
          String[] roles = decodedJwt.getClaim("roles").asArray(String.class);
          Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
          Stream.of(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
          UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          filterChain.doFilter(request, response);
        } catch (Exception ex) {
          log.error("Error logging in: {}", ex.getMessage());
          response.setHeader("error", ex.getMessage());
          response.setStatus(FORBIDDEN.value());

          Map<String, String> error = new HashMap<>();
          error.put("error_message", ex.getMessage());

          response.setContentType(APPLICATION_JSON_VALUE);
          new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
