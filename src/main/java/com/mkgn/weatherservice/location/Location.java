package com.mkgn.weatherservice.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Location {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id; // todo you can try to use UUID instead of long
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;

//    @Enumerated(EnumType.STRING)
//    private Cardinals lat_cardinal; // todo it is unnecessary, you can calculate it based on latitude valueg
//
//    //  lat_cardinal -> latitudeCardinal (full name + camelCase for variables) (UPPER CASE + snake_case for static finals)
//    @Enumerated(EnumType.STRING)
//    private Cardinals lon_cardinal; // todo it is unnecessary, you can calculate it based on longitude value
}
