package com.mkgn.weatherservice.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class LocationServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationRepository locationRepository;

    @Test
    void createLocation_createsNewLocation() throws Exception {
        //given
        locationRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("city")
                .country("country")
                .region("region")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(locationRepository.findAll()).singleElement().satisfies(entry -> {
            assertThat(entry.getCity()).isEqualTo("city");
            assertThat(entry.getCountry()).isEqualTo("country");
            assertThat(entry.getRegion()).isEqualTo("region");
            assertThat(entry.getLatitude()).isEqualTo(54.0);
            assertThat(entry.getLongitude()).isEqualTo(18.0);
        });
    }

    @Test
    void createLocation_whenCityIsEmpty_returns400StatusCode() throws Exception {
        // given
        locationRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("")
                .country("country")
                .region("region")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void createLocation_whenRegionIsEmpty_returns201StatusCode() throws Exception {
        // given
        locationRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("city")
                .country("country")
                .region(" ")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(locationRepository.findAll()).isNotNull();
        assertThat(locationRepository.findAll()).singleElement().satisfies(entry -> {
            assertThat(entry.getRegion()).isNull();
        });
    }

    @Test
    void createLocation_whenRegionIsEmpty_returnsNewLocationWithNullRegion() throws Exception {
        // given
        locationRepository.deleteAll();
        ObjectMapper objectMapper = new ObjectMapper();
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("city")
                .country("country")
                .region("        ")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(locationRepository.findAll()).singleElement().satisfies(entry -> {
            assertThat(entry.getRegion()).isNull();
        });
    }
}
