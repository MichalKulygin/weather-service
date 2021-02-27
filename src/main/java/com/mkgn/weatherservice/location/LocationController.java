package com.mkgn.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/addLocation")
    ResponseEntity<NewLocationResponse> postLocation(@RequestBody CreateLocationRequest request) {

        String city = request.getCity();
        String region = request.getRegion();
        String country = request.getCountry();
        Double lat = request.getLat();
        Double lon = request.getLon();

        Location location = locationService.createLocation(city, region, country, lat, lon);

        NewLocationResponse responseBody = new NewLocationResponse(
                city,
                region,
                country,
                lat,
                lon,
                location.getLat_cardinal().name(),
                location.getLon_cardinal().name());

        return ResponseEntity.status(200).body(responseBody);

    }

}
