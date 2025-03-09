package com.agent.controller;

import com.agent.service.WeatherChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Mock
    private WeatherChatService weatherChatService;

    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        weatherController = new WeatherController(weatherChatService);
    }

    @Test
    void testCitiesWeather_ValidMessage() {

        String message = "weather in cardiff";
        String expectedResponse = "Sunny weather in Cardiff";
        when(weatherChatService.getWeatherResponse(message)).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = weatherController.citiesWeather(message);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testCitiesWeather_EmptyMessage() {

        String message = "";

        ResponseEntity<String> responseEntity = weatherController.citiesWeather(message);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Query parameter 'message' is required.", responseEntity.getBody());
    }

    @Test
    void testCitiesWeather_NullMessage() {

        String message = null;

        ResponseEntity<String> responseEntity = weatherController.citiesWeather(message);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Query parameter 'message' is required.", responseEntity.getBody());
    }
}
