# Build AI Agent using Spring AI (Functions)

This AI agents is build to fetch real-time weather updates using function calling functionality of Spring AI. 
 
More details about function calling in spring AI can be found in this
[article](#https://medium.com/version-1/enhance-your-ai-agents-capabilities-using-functions-in-spring-ai-c73ba7596739)

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup and Configuration](#setup-and-configuration)
- [API Usage](#api-usage)
- [How It Works](#how-it-works)
- [Disclaimer](#disclaimer)

## Overview

This project uses Spring Boot to create a RESTful service that accepts natural language queries for weather information. It leverages Spring AI's chat client and function-calling mechanism to trigger a weather service that fetches current weather data from an external API. The application uses the llama LLM (local AI model) by default, which can be replaced or reconfigured as needed.

## Features

- **Chat-based Weather Queries**: Ask for current weather using natural language (e.g., "weather in cardiff").
- **Function Calling**: The AI chat client calls a registered function (`currentWeatherFunction`) to retrieve live weather data.
- **Real-time Data Fetching**: Uses a weather API (configured via application properties) to obtain current weather information.
- **Modular Design**: Clean separation of concerns with dedicated components for configuration, API integration, and AI chat functionality.
## Prerequisites

- **Java 17 or later**: The project uses Java records and modern features.
- **Gradle**: Gradle is used as build tool to build and run the application.
- **Weather API Key**: Obtain an API key from a weather provider (e.g., [WeatherAPI.com](https://www.weatherapi.com/)).
- **Ollama**: Install and configure [Ollama](https://ollama.ai/) for local LLM usage (or adjust the configuration to use another AI provider).

## Setup and Configuration

1. **Clone the Repository**
   ```bash
   git clone https://github.com/AzamMehr/springAi-agent.git
2. **Configure Application Properties**
    weather.apiKey=YOUR_WEATHER_API_KEY
    weather.apiUrl=https://api.weatherapi.com/v1

## API Usage
- **Endpoint**: *GET* "/weather"
- **Query Parameter**: 
  *message*: The natural language query, e.g., weather in cardiff.
  ```bash
   curl -X GET "http://localhost:8080/weather?message=weather in cardiff"
- **Example Response**
    ```bash
   Current weather in Cardiff: 15°C, partly cloudy, with a wind speed of 10 mph.

## How It Works
1. **AI Chat Client:** The WeatherController uses a chat client built from ChatClient.Builder and a system message to define the AI’s role.
2. **Function Calling:** When a weather query is received, the chat client triggers the registered currentWeatherFunction (implemented by CurrentWeatherService) to fetch live data.
3. **Data Integration:** The weather data retrieved from the external API is then integrated into the AI-generated response and returned to the user.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Disclaimer
This project is for educational and demonstration purposes only. Weather data is fetched from a third-party API; please ensure you comply with the provider's terms of service. 