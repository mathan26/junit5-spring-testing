package com.mathan26.github.junit5springtesting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HeroNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleHeroNotExistException(){

    }
}
