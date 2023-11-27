package com.temple.api.exception;

import org.springframework.http.HttpStatus;

public class CommonAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public CommonAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
