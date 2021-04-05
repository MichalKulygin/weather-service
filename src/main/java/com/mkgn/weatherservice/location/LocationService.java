package com.mkgn.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LocationService {

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double latitude, Double longitude) {
        Location location = new Location();
        location.setCity(city);
        location.setRegion(region);
        location.setCountry(country);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return locationRepository.save(location);
    }
}
