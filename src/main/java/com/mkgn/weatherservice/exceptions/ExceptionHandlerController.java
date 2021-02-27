package com.mkgn.weatherservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EmptyInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequestException(EmptyInputException e) {
        System.out.println(e.getMessage());
    }

    // todo CardinalsOutOfRangeException will be mapped to InternalServerError - 500 status code
    //  you have to handle it as well
}
