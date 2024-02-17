package com.demo.tasklist.controller;

import com.demo.tasklist.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            WebRequest webRequest
    ) {
        log.error("Caught MethodArgumentNotValidException for request {}", webRequest);
        log.error("Caught MethodArgumentNotValidException", e);

        StringBuilder resultBuilder = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        errors.forEach(objectError -> resultBuilder.append(Objects.requireNonNull(objectError.getDefaultMessage())));
        return ResponseEntity.badRequest().body(resultBuilder.toString());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<?> handleMethodArgumentNotValidException(
            ObjectNotFoundException e,
            WebRequest webRequest
    ) {
        log.error("Caught ObjectNotFoundException for request {}", webRequest);
        log.error("Caught ObjectNotFoundException", e);
        return ResponseEntity.notFound().build();
    }

}
