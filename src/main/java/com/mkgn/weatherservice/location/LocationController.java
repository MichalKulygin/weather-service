package com.mkgn.weatherservice.location;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
class LocationController {
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @PostMapping("/location")
    ResponseEntity<NewLocationResponse> postLocation(@RequestBody @Valid CreateLocationRequest request) {
        String city = request.getCity();
        String region = request.getRegion();
        String country = request.getCountry();
        Double latitude = request.getLatitude();
        Double longitude = request.getLongitude();

        Location location = locationService.createLocation(city, region, country, latitude, longitude);

        NewLocationResponse responseBody = locationMapper.mapLocationToNewLocationResponse(location);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
