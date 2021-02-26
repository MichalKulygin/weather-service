package com.mkgn.weatherservice.location;

public enum LatCardinals {
    NORTH("N"),
    SOUTH("S");

    private String latCardinalFullName;

    LatCardinals(String cardinalFullName) {
        this.latCardinalFullName = cardinalFullName;
    }
}
