package dev.tojoos.helpnow.controllers;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tojoos.helpnow.model.Role;
import dev.tojoos.helpnow.model.User;
import dev.tojoos.helpnow.services.UserService;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller exposing user entities, managing registration and login procedures.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
  private String jwtSecret = "your_secret_here";

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/list")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User foundUser = userService.getById(id);
    return new ResponseEntity<>(foundUser, HttpStatus.OK);
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    User foundUser = userService.getByUsername(username);
    return new ResponseEntity<>(foundUser, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<User> addUser(@RequestBody User user) {
    User addedUser = userService.add(user);
    return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updatedUser = userService.update(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/role/add")
  public ResponseEntity<Role> addRole(@RequestBody Role role) {
    Role savedRole = userService.addRole(role);
    return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
  }

  @PostMapping("/role/addToUser")
  public ResponseEntity<?> addRoleToUser(@RequestBody String username, @RequestBody String roleName) {
    userService.addRoleToUser(username, roleName);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { //it is token auth
      try {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes()); //same secret that encoded the refreshToken
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJwt = verifier.verify(refreshToken);
        String username = decodedJwt.getSubject();
        User foundUser = userService.getByUsername(username);

        String accessToken = JWT.create()
                .withSubject(foundUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) //30 min accessToken
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", foundUser.getRoles().stream().map(Role::getName).toList())
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        log.info("Regenerating access and refresh token for user: " + foundUser.getUsername());

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
      } catch (Exception ex) {
        response.setHeader("error", ex.getMessage());
        response.setStatus(FORBIDDEN.value());

        Map<String, String> error = new HashMap<>();
        error.put("error_message", ex.getMessage());

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("Refresh token is missing.");
    }
  }
}
