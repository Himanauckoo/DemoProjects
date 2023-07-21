package com.chartercommunication.rewards.exceptions;

import org.springframework.http.HttpStatus;

public class RewardsExceptions extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    RewardsExceptions(String message, HttpStatus httpStatus) {

        this.message = message;
        this.httpStatus = httpStatus;
    }
}
