package com.chartercommunication.rewards.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CharterCommExceptionHandler {

   @ExceptionHandler(value = CustomerExceptions.class)
    public ResponseEntity<String> handleCustomException(CustomerExceptions customerExceptions){
       return ResponseEntity.status(412).body(customerExceptions.getMessage());
   }

    @ExceptionHandler(value = RewardsExceptions.class)
    public ResponseEntity<String> handleCustomException(RewardsExceptions rewardsExceptions){
        return ResponseEntity.status(412).body(rewardsExceptions.getMessage());
    }

    @ExceptionHandler(value = TransactionExceptions.class)
    public ResponseEntity<String> handleCustomException(TransactionExceptions transactionExceptions){
        return ResponseEntity.status(transactionExceptions.getHttpStatus()).body(transactionExceptions.getMessage());
    }
}
