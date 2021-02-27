package com.mkgn.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double lat, Double lon) {

        //TODO
        //input data validation

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLat(lat);
        location.setLon(lon);

        location.setLat_cardinal(Cardinals.NORTH);
        location.setLon_cardinal(Cardinals.EAST);

        return locationRepository.save(location);
    }

}
