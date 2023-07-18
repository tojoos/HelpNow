package dev.tojoos.helpnow.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

/**
 * Record used for Api Error representation.
 *
 * @author Jan Olszówka
 * @version 1.0
 * @since 2023-05-06
 */
public record ApiError(
    String path,
    HttpStatus status,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd-MM-yyyy")
    LocalDateTime localDateTime,
    String message
) {
}
