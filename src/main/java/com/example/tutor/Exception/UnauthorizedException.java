package com.example.tutor.Exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException{
    public UnauthorizedException (String message){
        super(message, HttpStatus.UNAUTHORIZED,"UNAUTHORIZED_ACCESS");
    }
}
