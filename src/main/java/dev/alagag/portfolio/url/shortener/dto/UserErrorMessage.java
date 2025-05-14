package dev.alagag.portfolio.url.shortener.dto;

import dev.alagag.portfolio.url.shortener.exception.UserErrorCodes;

public record UserErrorMessage(int code, String message, String details) {

    public UserErrorMessage(UserErrorCodes code, String details) {
        this(code.getValue(), code.getDefaultMessage(), details);
    }

}
