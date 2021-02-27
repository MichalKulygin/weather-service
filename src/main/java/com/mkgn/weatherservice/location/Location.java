package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String region;
    private String country;
    private Double lat;
    private Double lon;

    @Enumerated(EnumType.STRING)
    private Cardinals lat_cardinal;

    @Enumerated(EnumType.STRING)
    private Cardinals lon_cardinal;
}
