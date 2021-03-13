package com.mkgn.weatherservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(EmptyInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequestException(EmptyInputException e) {
        log.warn(e.getMessage());
    }

    @ExceptionHandler(CardinalsOutOfRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleCardinalsOutOfRangeException(CardinalsOutOfRangeException e) {
        System.out.println(e.getMessage());
        log.warn(e.getMessage());
    }
}
