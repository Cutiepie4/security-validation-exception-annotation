package com.ptit.viet.identityservice.exception;

import com.ptit.viet.identityservice.model.dto.response.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<String>> handleException(ApiException e) {
        return ResponseEntity.status(e.getCode()).body(ApiResponse.<String>builder().message(e.getMessage()).code(e.getCode()).build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ApiResponse.<String>builder().message(e.getFieldError().getDefaultMessage()).build());
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.badRequest().body(ApiResponse.<String>builder().message(e.getMessage()).build());
    }
}
