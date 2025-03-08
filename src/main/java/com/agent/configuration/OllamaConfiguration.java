package com.agent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.ollama.api.OllamaOptions;

@Configuration
public class OllamaConfiguration {

    @Bean
    public OllamaOptions.Builder ollamaOptionsBuilder() {
        return OllamaOptions.builder();
    }
}
