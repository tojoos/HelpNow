package dev.tojoos.helpnow.exceptions;

import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Default exception handler for the REST apis.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-05-06
 */
@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
    ApiError apiError = new ApiError(
        request.getRequestURI(),
        HttpStatus.NOT_FOUND,
        LocalDateTime.now(),
        ex.getMessage()
    );

    return new ResponseEntity<>(apiError, apiError.status());
  }

  @ExceptionHandler(InsufficientAuthenticationException.class)
  public final ResponseEntity<Object> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex, HttpServletRequest request) {
    ApiError apiError = new ApiError(
        request.getRequestURI(),
        HttpStatus.UNAUTHORIZED,
        LocalDateTime.now(),
        ex.getMessage()
    );

    return new ResponseEntity<>(apiError, apiError.status());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public final ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
    ApiError apiError = new ApiError(
        request.getRequestURI(),
        HttpStatus.UNAUTHORIZED,
        LocalDateTime.now(),
        ex.getMessage()
    );

    return new ResponseEntity<>(apiError, apiError.status());
  }
}
