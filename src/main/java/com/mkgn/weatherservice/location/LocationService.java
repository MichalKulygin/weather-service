package com.mkgn.weatherservice.location;

import com.mkgn.weatherservice.exceptions.CardinalsOutOfRangeException;
import com.mkgn.weatherservice.exceptions.EmptyInputException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LocationService {

    private final double NORTH_LIMIT = 90.0;
    private final double SOUTH_LIMIT = -90.0;
    private final double EAST_LIMIT = 180.0;
    private final double WEST_LIMIT = -180.0;

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {

        validateCity(city);
        validateCountry(country);
        validateLatitude(latitude);
        validateLongitude(longitude);

        if (StringUtils.isBlank(region)) {
            region = null;
        }

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return locationRepository.save(location);
    }

    private void validateCity(String city) {
        if (StringUtils.isBlank(city)) {
            throw new EmptyInputException("City cannot be empty or null");
        }
    }

    private void validateCountry(String country) {
        if (StringUtils.isBlank(country)) {
            throw new EmptyInputException("City cannot be empty or null");
        }
    }

    private void validateLatitude(Double latitude) {
        if (latitude < SOUTH_LIMIT || latitude > NORTH_LIMIT) {
            if (latitude > NORTH_LIMIT) {
                throw new CardinalsOutOfRangeException("cardinals out of range! latitude to high");
            } else {
                throw new CardinalsOutOfRangeException("cardinals out of range! latitude to low");
            }
        }
    }

    private void validateLongitude(Double longitude) {

        if (longitude > EAST_LIMIT || longitude < WEST_LIMIT) {
            if (longitude > EAST_LIMIT) {
                throw new CardinalsOutOfRangeException("cardinals out of range! longitude to high");
            } else {
                throw new CardinalsOutOfRangeException("cardinals out of range! longitude to low");
            }
        }
    }
}
