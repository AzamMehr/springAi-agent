package com.agent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherChatServiceTest {

    @Mock
    private ChatClient.Builder chatClientBuilder;

    @Mock
    private DefaultChatClient chatClient;

    @Mock
    ChatClient.CallResponseSpec callResponseSpec;

    @Mock
    private OllamaOptions.Builder ollamaOptionsBuilder;

    @Mock
    private ChatClient promptCallMock;

    @Mock
    private ChatClient.ChatClientRequestSpec responseMock;

    @InjectMocks
    private WeatherChatService weatherChatService;

    @BeforeEach
    void setUp() {

        doReturn(chatClient).when(chatClientBuilder).build();
        doReturn(OllamaOptions.builder().build()).when(ollamaOptionsBuilder).build();
        when(chatClientBuilder.build()).thenReturn(chatClient);

        when(ollamaOptionsBuilder.build()).thenReturn(OllamaOptions.builder().build());

        when(chatClient.prompt(any(Prompt.class))).thenReturn(responseMock);
        when(responseMock.functions(anyString())).thenReturn(responseMock);
        when(responseMock.call()).thenReturn(callResponseSpec);

        weatherChatService = new WeatherChatService(chatClientBuilder, ollamaOptionsBuilder);
    }

    @Test
    public void testGetWeatherResponse_Success() {

        String expectedResponse = "It is sunny in New York.";
        when(callResponseSpec.content()).thenReturn(expectedResponse);

        String result = weatherChatService.getWeatherResponse("weather in New York");

        assertEquals(expectedResponse, result);
    }

    @Test
    public void testGetWeatherResponse_FallbackMessage() {

        String fallbackResponse = "This functionality is not yet available.";
        when(callResponseSpec.content()).thenReturn(fallbackResponse);

        String result = weatherChatService.getWeatherResponse("weather on Mars");

        assertEquals(fallbackResponse, result);
    }

    @Test
    public void testGetWeatherResponse_ErrorHandling() {

        when(responseMock.call()).thenThrow(new RuntimeException("AI service error"));

        try {
            String result = weatherChatService.getWeatherResponse("weather in Tokyo");
        } catch (Exception e) {
            assertEquals("AI service error", e.getMessage());
        }
    }
}
