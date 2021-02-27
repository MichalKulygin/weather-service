package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationRequest { // todo let's try avoid public modifier

    private String city;
    private String region;
    private String country;
    private Double lat; // todo use full name
    private Double lon; // todo use full name
}
