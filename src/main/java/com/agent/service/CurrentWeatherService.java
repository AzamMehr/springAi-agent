package com.agent.service;

import com.agent.configuration.WeatherApiConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

public class CurrentWeatherService implements Function<CurrentWeatherService.Request, CurrentWeatherService.Response>{

    public static  final Logger log = LoggerFactory.getLogger(CurrentWeatherService.class);

    private final WeatherApiConfigProperties weatherConfigProperties;
    private final RestClient restClient;


    public CurrentWeatherService(WeatherApiConfigProperties weatherConfigProperties) {
        this.weatherConfigProperties = weatherConfigProperties;
        this.restClient = RestClient.create(weatherConfigProperties.apiUrl());
    }

    @Override
    public Response apply(Request request) {
        log.info("Weather request {}",request);
        Response response = restClient.get()
                .uri("/current.json?key={key}&q={q}", weatherConfigProperties.apiKey(), request.city())
                .retrieve()
                .body(Response.class);
        log.info("Weather Api response : {}",response);
        return response;
    }

    public record Request(String city){}
    public record Response(Location location, Current current){}
    public record Location(String name, String region, String country, Long lat, Long lon){}
    public record Current(String temp_f, Condition condition, String wind_mph, String humidity){}
    public record Condition(String text){}
}

