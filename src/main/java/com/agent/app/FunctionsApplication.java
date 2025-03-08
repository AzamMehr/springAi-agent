package com.agent.app;

import com.agent.configuration.WeatherApiConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(WeatherApiConfigProperties.class)
@ComponentScan(basePackages = {"com.agent"})
public class FunctionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FunctionsApplication.class, args);
    }
}
