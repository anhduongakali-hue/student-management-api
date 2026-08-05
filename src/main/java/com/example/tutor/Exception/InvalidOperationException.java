package com.example.tutor.Exception;

import org.springframework.http.HttpStatus;

public class InvalidOperationException extends BaseException{
    public InvalidOperationException(String message){
        super(message,HttpStatus.BAD_REQUEST,"INVALID_OPERATION");
    }
}
