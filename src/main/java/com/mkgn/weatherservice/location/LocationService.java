package com.mkgn.weatherservice.location;

import com.mkgn.weatherservice.exceptions.EmptyInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double lat, Double lon) {

        if (city.isEmpty()) {
            throw new EmptyInputException("Empty value not allowed");
        }
        if (country.isEmpty()) {
            throw new EmptyInputException("Empty value not allowed");
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
