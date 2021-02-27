package com.mkgn.weatherservice.exceptions;

public class EmptyInputException extends RuntimeException {
    public EmptyInputException(String message) {
        super(message);
    }
}
