package com.example.scottishhills.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException e) {
        return constructResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> constructResponseEntity(Exception e, HttpStatus status) {

        Map<String, Object> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(), // server local default zone id
                "message", e.getLocalizedMessage(),
                "type", e.getClass().getCanonicalName()
        );

        return new ResponseEntity<>(body, status);

    }

}
