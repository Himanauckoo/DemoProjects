package com.chartercommunication.rewards.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerExceptions extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
    public CustomerExceptions(String message, HttpStatus httpStatus){
        this.httpStatus=httpStatus;
        this.message=message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
