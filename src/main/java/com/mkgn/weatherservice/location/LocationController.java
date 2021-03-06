package com.mkgn.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class LocationController {
    private final LocationService locationService;

    @PostMapping("/location")
    ResponseEntity<NewLocationResponse> postLocation(@RequestBody CreateLocationRequest request) {
        String city = request.getCity();
        String region = request.getRegion();
        String country = request.getCountry();
        Double latitude = request.getLatitude();
        Double longitude = request.getLongitude();

        Location location = locationService.createLocation(city, region, country, latitude, longitude);

        NewLocationResponse responseBody = new NewLocationResponse(
                location.getId(),
                city,
                region,
                country,
                latitude,
                longitude,
                latitudeCardinal(latitude),
                longitudeCardinal(longitude));
        return ResponseEntity.status(200).body(responseBody);
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
