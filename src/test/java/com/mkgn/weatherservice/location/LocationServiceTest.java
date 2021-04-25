package com.mkgn.weatherservice.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    LocationService locationService;
    @Captor
    ArgumentCaptor<Location> locationArgumentCaptor;

    @Test
    void createLocation_thenCreatesNewLocation() {
        // given
        when(locationRepository.save(any())).thenReturn(
                Location.builder()
                        .city("City")
                        .region("Region")
                        .country("Country")
                        .latitude(54.0)
                        .longitude(18.0)
                        .build());
        // when
        locationService.createLocation("City", "Region", "Country", 54.0, 18.0);
        //then
        verify(locationRepository).save(locationArgumentCaptor.capture());
        Location location = locationArgumentCaptor.getValue();
        assertThat(location.getCity().equals("City"));
        assertThat(location.getRegion().equals("Region"));
        assertThat(location.getCountry().equals("Country"));
        assertThat(location.getLatitude().equals(54.0));
        assertThat(location.getLongitude().equals(18.0));
    }

    @Test
    void createLocation_whenRegionIsEmpty_thenCreatesNewLocation() {
        // given
        when(locationRepository.save(any())).thenReturn(
                Location.builder()
                        .city("City")
                        .region("")
                        .country("Country")
                        .latitude(54.0)
                        .longitude(18.0)
                        .build());
        // when
        locationService.createLocation("City", "", "Country", 54.0, 18.0);
        //then
        verify(locationRepository).save(locationArgumentCaptor.capture());
        Location location = locationArgumentCaptor.getValue();
        assertThat(location.getCity().equals("City"));
        assertThat(location.getRegion()).isNull();
        assertThat(location.getCountry().equals("Country"));
        assertThat(location.getLatitude().equals(54.0));
        assertThat(location.getLongitude().equals(18.0));
    }
}