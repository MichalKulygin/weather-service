package com.mkgn.weatherservice.location;

public enum Cardinals {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private String latCardinalFullName;

    Cardinals(String cardinalFullName) {
        this.latCardinalFullName = cardinalFullName;
    }
}
