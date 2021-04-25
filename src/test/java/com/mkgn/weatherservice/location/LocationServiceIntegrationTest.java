package com.mkgn.weatherservice.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
    LocationService locationService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        locationRepository.deleteAll();
    }

    @Test
    void postLocation_thenReturns201AndSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("City")
                .region("Region")
                .country("Country")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(locationRepository.findAll()).singleElement().satisfies(location -> {
            assertThat(location.getId()).isInstanceOf(String.class);
            assertThat(location.getCity()).isEqualTo("City");
            assertThat(location.getRegion()).isEqualTo("Region");
            assertThat(location.getCountry()).isEqualTo("Country");
            assertThat(location.getLatitude()).isEqualTo(54.0);
            assertThat(location.getLongitude()).isEqualTo(18.0);
        });
    }

    @Test
    void postLocation_whenCityIsNull_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city(null)
                .region("Region")
                .country("Country")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCityIsEmpty_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("")
                .region("Region")
                .country("Country")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCityIsBlank_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("   ")
                .region("Region")
                .country("Country")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCountryIsNull_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("City")
                .region("Region")
                .country(null)
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCountryIsEmpty_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("City")
                .region("Region")
                .country("")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCountryIsBlank_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("City")
                .region("Region")
                .country("   ")
                .latitude(54.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenLatitudeIsOutOfRange_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("City")
                .region("Region")
                .country("Country")
                .latitude(91.0)
                .longitude(18.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenLongitudeIsOutOfRange_thenReturns400AndDoesntSaveNewLocationInDb() throws Exception {
        // given
        CreateLocationRequest requestBody = CreateLocationRequest.builder()
                .city("City")
                .region("Region")
                .country("Country")
                .latitude(54.0)
                .longitude(181.0)
                .build();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }
}