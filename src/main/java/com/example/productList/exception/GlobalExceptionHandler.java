package com.example.productList.exception;

import com.example.productList.dto.ExceptionDTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ExceptionDTOResponse> handlerCategoryAlreadyExistException(
            CategoryAlreadyExistException e,
            WebRequest webRequest
    ){
        ExceptionDTOResponse exceptionDTOResponse = new ExceptionDTOResponse(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTOResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDTOResponse> handleCategoryNotFoundException(
            CategoryNotFoundException e,
            WebRequest webRequest
    ){

        ExceptionDTOResponse exceptionDTOResponse = new ExceptionDTOResponse(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTOResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTOResponse> handleGlobalException(
            Exception e,
            WebRequest webRequest
    ){
        ExceptionDTOResponse exceptionDTOResponse = new ExceptionDTOResponse(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTOResponse);
    }
}
