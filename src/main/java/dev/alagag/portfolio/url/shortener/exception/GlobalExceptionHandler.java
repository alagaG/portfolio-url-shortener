package dev.alagag.portfolio.url.shortener.exception;

import dev.alagag.portfolio.url.shortener.dto.UserErrorMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserErrorMessageException.class)
    public ResponseEntity<UserErrorMessage> handleUserErrorMessages(HttpServletResponse res, UserErrorMessageException e) {
        UserErrorCodes code = e.getCode();
        UserErrorMessage dto = e.createDTO();
        return ResponseEntity
                .status(code.getHttpStatus())
                .body(dto);
    }

}
