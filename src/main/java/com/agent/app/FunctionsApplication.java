package com.agent.app;

import com.agent.functions.WeatherApiConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(WeatherApiConfigProperties.class)
@SpringBootApplication
public class FunctionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FunctionsApplication.class, args);
    }
}
