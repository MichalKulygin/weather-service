package com.mkgn.weatherservice.location;

public enum Cardinals {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    EQUATOR("EQ"),
    PRIME_MERIDIAN("PM");

    private final String cardinalAbbreviation;

    Cardinals(String cardinalFullName) {
        this.cardinalAbbreviation = cardinalFullName;
    }

    public String getCardinalAbbreviation() {
        return cardinalAbbreviation;
    }
}
