package com.agent.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionConfiguration {

    private final WeatherApiConfigProperties weatherConfigProperties;

    public FunctionConfiguration(WeatherApiConfigProperties weatherConfigProperties) {
        this.weatherConfigProperties = weatherConfigProperties;
    }


    @Bean
    @Description("Get the current weather condition for the given city")
    public Function<CurrentWeatherService.Request, CurrentWeatherService.Response> currentWeatherFunction(){
        return new CurrentWeatherService(weatherConfigProperties);
    }
}
