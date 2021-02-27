package com.mkgn.weatherservice.exceptions;

public class CardinalsOutOfRangeException extends RuntimeException {
    public CardinalsOutOfRangeException(String message) {
        super(message);
    }
}
