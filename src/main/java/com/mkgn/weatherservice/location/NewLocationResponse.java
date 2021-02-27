package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NewLocationResponse {
    private String city;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    private String lat_cardinal;
    private String lon_cardinal;

}
