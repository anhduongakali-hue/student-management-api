package com.example.tutor.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends BaseException  {
    public DuplicateResourceException(String message){
        super(message, HttpStatus.CONFLICT,"DUPLICATE_RESOURCE");
    }
}
