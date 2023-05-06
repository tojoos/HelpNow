package dev.tojoos.helpnow.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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
