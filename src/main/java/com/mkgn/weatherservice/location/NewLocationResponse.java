package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
class NewLocationResponse {
    private UUID id;
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
    private String latitudeCardinal;
    private String longitudeCardinal;

    // todo - model required by frontend
    //  id - number (string for UUID), city - string, region - string, country - string, longitude - string (enum), latitude - string (enum)
}
