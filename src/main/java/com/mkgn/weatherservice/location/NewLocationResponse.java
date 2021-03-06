package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class NewLocationResponse {
    private String id;
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
    private String latitudeCardinal;
    private String longitudeCardinal;
}
