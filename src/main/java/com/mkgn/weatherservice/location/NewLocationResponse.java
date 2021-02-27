package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NewLocationResponse { // todo let's try avoid public modifier
    private String city;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    private String lat_cardinal;
    private String lon_cardinal;

    // todo - model required by frontend
    //  id - number (string for UUID), city - string, region - string, country - string, longitude - string (enum), latitude - string (enum)
    //  enum - "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
}
