package com.mkgn.weatherservice.location;


import com.mkgn.weatherservice.exceptions.CardinalsOutOfRangeException;
import com.mkgn.weatherservice.exceptions.EmptyInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    LocationService locationService;

    @Test
    void createLocation_createsNewLocation() {
        //given
        Location gdynia = Location.builder()
                .city("Gdynia")
                .region("pomorskie")
                .country("Poland")
                .latitude(54.0)
                .longitude(18.5)
                .build();
        when(locationRepository.save(any())).thenReturn(gdynia);

        //when
        Location location = locationService.createLocation("city", "region", "Poland", 54.0, 18.5);

        //then
        assertThat(location.getCity()).isEqualTo("Gdynia");
        assertThat(location.getRegion()).isEqualTo("pomorskie");
        assertThat(location.getCountry()).isEqualTo("Poland");
        assertThat(location.getLatitude()).isEqualTo(54.0);
        assertThat(location.getLongitude()).isEqualTo(18.5);
        verify(locationRepository).save(any());
    }

    @Test
    void createLocation_whenCityIsEmpty_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation(" ", "pomorskie", "Poland", 54.0, 18.0));

        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenCityIsNull_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation(null, "pomorskie", "Poland", 54.0, 18.0));

        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenCountyIsEmpty_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation("Tczew", "pomorskie", " ", 54.0, 18.0));

        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenCountyIsNull_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation("Tczew", "pomorskie", null, 54.0, 18.0));

        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLatitudeIsLowerThenLimit_throwsCardinalOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation("city", "region", "country", -91.0, 18.0));

        // then
        assertThat(exception).isInstanceOf(CardinalsOutOfRangeException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLatitudeIsHigherThenLimit_throwsCardinalOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation("city", "region", "country", 91.0, 18.0));

        // then
        assertThat(exception).isInstanceOf(CardinalsOutOfRangeException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLongitudeIsLowerThenLimit_throwsCardinalOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation("city", "region", "country", 54.0, -181.0));

        // then
        assertThat(exception).isInstanceOf(CardinalsOutOfRangeException.class);
        verify(locationRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLongitudeIsHigherThenLimit_throwsCardinalOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationService.createLocation("city", "region", "country", 54.0, 181.0));

        // then
        assertThat(exception).isInstanceOf(CardinalsOutOfRangeException.class);
        verify(locationRepository, times(0)).save(any());
    }


    //TODO how to test empty or null region?
    //    void createLocation_whenRegionIsEmpty_createsNewLocationWithNullRegion(){}
    //    void createLocation_whenRegionIsNull_createsNewLocationWithNullRegion(){}
}
