package com.josef.weathertracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBad(IllegalArgumentException ex) {
        return err(HttpStatus.BAD_REQUEST, "Invalid Input Data", ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleBadGateway(IllegalStateException ex) {
        return err(HttpStatus.BAD_GATEWAY, "Weather API Error", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleFail(Exception ex) {
        return err(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", "System crash.");
    }

    private ResponseEntity<Map<String, Object>> err(HttpStatus status, String error, String msg) {
        return ResponseEntity.status(status).body(Map.of(
                "timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT),
                "status", status.value(),
                "error", error,
                "message", msg));
    }
}