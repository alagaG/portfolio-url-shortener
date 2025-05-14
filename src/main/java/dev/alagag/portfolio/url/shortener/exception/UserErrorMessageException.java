package dev.alagag.portfolio.url.shortener.exception;

import dev.alagag.portfolio.url.shortener.dto.UserErrorMessage;
import org.springframework.lang.Nullable;

public class UserErrorMessageException extends RuntimeException {
    private final UserErrorCodes code;
    private String message;
    @Nullable
    private String details;

    public UserErrorMessageException(UserErrorCodes code, String message, @Nullable String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public UserErrorMessageException(UserErrorCodes code, String details) {
        this(code, code.getDefaultMessage(), details);
    }

    public UserErrorMessageException(UserErrorCodes code) {
        this(code, null);
    }

    public UserErrorMessage createDTO() {
        return new UserErrorMessage(this.code.getValue(), this.message, this.details);
    }

    public UserErrorCodes getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Nullable
    public String getDetails() {
        return details;
    }
}
