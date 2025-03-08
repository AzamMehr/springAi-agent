package com.agent.configuration;

import com.agent.service.CurrentWeatherService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
@EnableConfigurationProperties(WeatherApiConfigProperties.class)
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
