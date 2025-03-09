package com.agent.app;

import com.agent.configuration.FunctionConfiguration;
import com.agent.configuration.OllamaConfiguration;
import com.agent.configuration.WeatherApiConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FunctionsApplicationTest {

    @Autowired
    private WeatherApiConfigProperties weatherApiConfigProperties;

    @Autowired
    private FunctionConfiguration functionConfiguration;

    @Autowired
    private OllamaConfiguration ollamaConfiguration;

    @Test
    public void testBeansLoaded() {
        assertNotNull(weatherApiConfigProperties, "WeatherApiConfigProperties bean should be loaded");

        Function<?, ?> weatherFunction = functionConfiguration.currentWeatherFunction();
        assertNotNull(weatherFunction, "Weather function bean should be loaded");

        assertNotNull(ollamaConfiguration.ollamaOptionsBuilder(), "OllamaOptions.Builder bean should be loaded");
    }
}
