package com.agent.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final ChatClient chatClient;
    private final SystemMessage systemMessage;
    private final OllamaOptions ollamaOptions;

    public WeatherController(ChatClient.Builder chatBuilder, OllamaOptions.Builder ollamaOptionsBuilder) {
        this.chatClient = chatBuilder.build();
        this.ollamaOptions = ollamaOptionsBuilder.build();
        systemMessage = new SystemMessage("You are a helpful AI agent answering questions" +
                " about current weather.Provide brief and precise answers. If you can't provide current" +
                " weather just answer by saying this functionality is not yet available");
    }

    @GetMapping("/weather")
    public String citiesWeather (@RequestParam(value = "message", defaultValue = "") String message) {
        UserMessage userMessage = new UserMessage(message);
        return chatClient
                .prompt(new Prompt(List.of(systemMessage,userMessage),ollamaOptions))
                .functions("currentWeatherFunction")
                .call().content();

    }
}
