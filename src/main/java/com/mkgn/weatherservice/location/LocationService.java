package com.mkgn.weatherservice.location;

import com.mkgn.weatherservice.exceptions.CardinalsOutOfRangeException;
import com.mkgn.weatherservice.exceptions.EmptyInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationService { // todo let's try avoid public modifier

    private final double NORTH_LIMIT = 90.0;
    private final double SOUTH_LIMIT = -90.0;
    private final double EAST_LIMIT = 180.0;
    private final double WEST_LIMIT = -180.0;

    private final LocationRepository locationRepository;

    Location createLocation(String city, String region, String country, Double lat, Double lon) {
        // todo under the method signature we don't put newline
        if (city.isEmpty()) {
            throw new EmptyInputException("Null value not allowed");
        }
        if (country.isEmpty()) {
            throw new EmptyInputException("Null value not allowed");
        }
        // todo I know we can use if statement in one line, but most of the tools for static code analysis eg. sonarqube report that approach
        if (lat > NORTH_LIMIT) throw new CardinalsOutOfRangeException("cardinals out of range! latitude to high");
        if (lat < SOUTH_LIMIT) throw new CardinalsOutOfRangeException("cardinals out of range! latitude to low");
        if (lon > EAST_LIMIT) throw new CardinalsOutOfRangeException("cardinals out of range! longitude to high");
        if (lon < WEST_LIMIT) throw new CardinalsOutOfRangeException("cardinals out of range! longitude to low");

        Location location = new Location();
        location.setCity(city);
        location.setRegion(region); // todo if user pass there an empty value (eg. "  ") you can set null
        location.setCountry(country);
        location.setLat(lat);
        location.setLon(lon);

/*
        location.setLat_cardinal(Cardinals.NORTH);  // todo commented code, dead code
        location.setLon_cardinal(Cardinals.EAST);
*/

        location.setLat_cardinal(lat >= 0 ? Cardinals.NORTH : Cardinals.SOUTH);
        location.setLon_cardinal(lon >= 0 ? Cardinals.EAST : Cardinals.WEST);

        return locationRepository.save(location);
    }

}
