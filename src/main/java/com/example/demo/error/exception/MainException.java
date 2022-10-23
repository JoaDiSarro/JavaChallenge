package com.example.demo.error.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class MainException extends RuntimeException {

    private final String errorMessage;

    @JsonCreator
    public MainException(final String errorMessage) {
        this(errorMessage, null);
    }

    public MainException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

}