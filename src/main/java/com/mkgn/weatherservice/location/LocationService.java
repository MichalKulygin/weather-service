package com.mkgn.weatherservice.location;

import com.mkgn.weatherservice.exceptions.CardinalsOutOfRangeException;
import com.mkgn.weatherservice.exceptions.EmptyInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LocationService {

    private final double NORTH_LIMIT = 90.0;
    private final double SOUTH_LIMIT = -90.0;
    private final double EAST_LIMIT = 180.0;
    private final double WEST_LIMIT = -180.0;

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double lat, Double lon) {
        if (city == null || city.isBlank()) {
            throw new EmptyInputException("City cannot be empty or null");
        }
        if (country == null || country.isBlank()) {
            throw new EmptyInputException("County cannot be empty or null");
        }
        if (lat > NORTH_LIMIT) {
            throw new CardinalsOutOfRangeException("cardinals out of range! latitude to high");
        }
        if (lat < SOUTH_LIMIT) {
            throw new CardinalsOutOfRangeException("cardinals out of range! latitude to low");
        }
        if (lon > EAST_LIMIT) {
            throw new CardinalsOutOfRangeException("cardinals out of range! longitude to high");
        }
        if (lon < WEST_LIMIT) {
            throw new CardinalsOutOfRangeException("cardinals out of range! longitude to low");
        }

        if (region.equals(" ")) {
            region = null;
        }

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLatitude(lat);
        location.setLongitude(lon);

        return locationRepository.save(location);
    }
}
