package com.agent.controller;

import com.agent.service.WeatherChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherChatService weatherChatService;

    public WeatherController(WeatherChatService weatherChatService) {
        this.weatherChatService = weatherChatService;
    }

    @GetMapping("/weather")
    public ResponseEntity<String> citiesWeather(@RequestParam(value = "message", defaultValue = "") String message) {
        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Query parameter 'message' is required.");
        }
        String response = weatherChatService.getWeatherResponse(message);
        return ResponseEntity.ok(response);
    }
}