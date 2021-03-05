package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class CreateLocationRequest {

    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
}
