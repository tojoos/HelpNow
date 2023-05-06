package dev.tojoos.helpnow.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        HttpStatus status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm:ss dd-MM-yyyy")
        LocalDateTime localDateTime,
        String message
) {
}
