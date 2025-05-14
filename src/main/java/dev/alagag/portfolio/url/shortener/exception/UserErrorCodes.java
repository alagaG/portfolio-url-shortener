package dev.alagag.portfolio.url.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum UserErrorCodes {
    INVALID_BODY(0, HttpStatus.BAD_REQUEST, "Malformed body data!"),
    ORIGINAL_URL_NOT_FOUND(1, HttpStatus.NOT_FOUND, "URL not found!");

    private final int value;
    private final String defaultMessage;
    private final HttpStatusCode httpStatus;

    UserErrorCodes(int value, HttpStatusCode httpStatus, String defaultMessage) {
        this.value = value;
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    public int getValue() {
        return value;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public HttpStatusCode getHttpStatus() {
        return httpStatus;
    }
}
