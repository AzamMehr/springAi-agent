package com.agent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherChatService {

    private final ChatClient chatClient;
    private final SystemMessage systemMessage;
    private final OllamaOptions ollamaOptions;

    public WeatherChatService(ChatClient.Builder chatClientBuilder,  OllamaOptions.Builder ollamaOptionBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.systemMessage = new SystemMessage("You are a helpful AI agent answering questions about current weather. " +
                "Provide brief and precise answers. If you can't provide current weather, just answer by saying this functionality is not yet available.");
        this.ollamaOptions = ollamaOptionBuilder.build();
    }

    public String getWeatherResponse(String userQuery) {
        UserMessage userMessage = new UserMessage(userQuery);
        return chatClient
                .prompt(new Prompt(List.of(systemMessage, userMessage), ollamaOptions))
                .functions("currentWeatherFunction")
                .call()
                .content();
    }
}
