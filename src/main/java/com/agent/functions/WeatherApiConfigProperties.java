package com.agent.functions;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "weather")
public record WeatherApiConfigProperties(String apiKey, String apiUrl) {
}
