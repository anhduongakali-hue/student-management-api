package com.example.tutor.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handlerValidationException(MethodArgumentNotValidException ex){
        log.warn("Dữ liệu đầu vào không hợp lệ . Trả lỗi danh sách cho Client .");

        Map<String , String > errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String , String>> handlerRuntimeException(RuntimeException ex){
        log.error("Lỗi runtime: " , ex);

        Map<String , String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}