package com.mkgn.weatherservice.location;

import com.mkgn.weatherservice.exceptions.CardinalsOutOfRangeException;
import com.mkgn.weatherservice.exceptions.EmptyInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationService {

    private final double NORTH_LIMIT = 90.0;
    private final double SOUTH_LIMIT = -90.0;
    private final double EAST_LIMIT = 180.0;
    private final double WEST_LIMIT = -180.0;

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double lat, Double lon) {

        if (city.isEmpty()) {
            throw new EmptyInputException("Null value not allowed");
        }
        if (country.isEmpty()) {
            throw new EmptyInputException("Null value not allowed");
        }

        if (lat > NORTH_LIMIT || lat < SOUTH_LIMIT) {
            throw new CardinalsOutOfRangeException("latitude out of range");
        }
        if (lon > EAST_LIMIT || lon > WEST_LIMIT) {
            throw new CardinalsOutOfRangeException("longitude out of range");
        }

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLat(lat);
        location.setLon(lon);

        //TODO
        //
        location.setLat_cardinal(Cardinals.NORTH);
        location.setLon_cardinal(Cardinals.EAST);

        return locationRepository.save(location);
    }

}
