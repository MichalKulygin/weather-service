package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location { // todo let's try avoid public modifier

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // todo you can try to use UUID instead of long
    private String city;
    private String region;
    private String country;
    private Double lat; // todo use full name
    private Double lon; // todo use full name

    @Enumerated(EnumType.STRING)
    private Cardinals lat_cardinal; // todo it is unnecessary, you can calculate it based on latitude value
                                    //  lat_cardinal -> latitudeCardinal (full name + camelCase for variables) (UPPER CASE + snake_case for static finals)
    @Enumerated(EnumType.STRING)
    private Cardinals lon_cardinal; // todo it is unnecessary, you can calculate it based on longitude value
}
