package com.mkgn.weatherservice.location;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@Builder
class CreateLocationRequest {

    @NotBlank(message = "City cannot be null or empty")
    private String city;
    private String region;
    @NotBlank(message = "Country cannot be null or empty")
    private String country;
    @Min(value = -90, message = "Wrong latitude value. Minimum latitude value = -90")
    @Max(value = 90, message = "Wrong latitude value. Maximum latitude value = 90")
    private Double latitude;
    @Min(value = -180, message = "Wrong longitude value. Minimum longitude value = -180")
    @Max(value = 180, message = "Wrong longitude value. Maximum longitude value = 180")
    private Double longitude;
}
