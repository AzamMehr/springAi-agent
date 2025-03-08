package com.agent.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "weather")
public record WeatherApiConfigProperties(String apiKey, String apiUrl) {
}
