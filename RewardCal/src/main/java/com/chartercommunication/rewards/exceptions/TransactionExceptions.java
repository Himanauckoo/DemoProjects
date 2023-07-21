package com.chartercommunication.rewards.exceptions;

import org.springframework.http.HttpStatus;

public class TransactionExceptions extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;
    public TransactionExceptions(String message){
        super(message);

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public TransactionExceptions(String message, HttpStatus httpStatus){
       this.message=message;
       this.httpStatus=httpStatus;
    }
}
