package com.mkgn.weatherservice.location;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
class CreateLocationRequest {

    @NotBlank(message = "City cannot be null or empty")
//    @NotNull
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
}
