package com.josef.weathertracker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("geocodingClient")
    public RestClient geocodingClient() {
        // Correct subdomain for geocoding searches
        return RestClient.builder().baseUrl("https://geocoding-api.open-meteo.com").build();
    }

    @Bean
    @Qualifier("weatherClient")
    public RestClient weatherClient() {
        // Correct subdomain for forecast requests
        return RestClient.builder().baseUrl("https://api.open-meteo.com").build();
    }
}