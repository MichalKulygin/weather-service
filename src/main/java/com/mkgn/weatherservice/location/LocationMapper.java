package com.mkgn.weatherservice.location;

import org.springframework.stereotype.Component;

@Component
class LocationMapper {

    NewLocationResponse mapLocationToNewLocationResponse(Location location) {

        return NewLocationResponse.builder().
                id(location.getId())
                .city(location.getCity())
                .region(location.getCity())
                .country(location.getCountry())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .latitudeCardinal(latitudeCardinal(location.getLatitude()))
                .longitudeCardinal(longitudeCardinal(location.getLongitude()))
                .build();
    }

    private String longitudeCardinal(Double longitude) {
        if (longitude > 0) {
            return Cardinals.EAST.getCardinalAbbreviation();
        } else if (longitude < 0) {
            return Cardinals.WEST.getCardinalAbbreviation();
        } else {
            return Cardinals.PRIME_MERIDIAN.getCardinalAbbreviation();
        }
    }

    private String latitudeCardinal(Double latitude) {
        if (latitude > 0) {
            return Cardinals.NORTH.getCardinalAbbreviation();
        } else if (latitude < 0) {
            return Cardinals.SOUTH.getCardinalAbbreviation();
        } else {
            return Cardinals.EQUATOR.getCardinalAbbreviation();
        }
    }

}
